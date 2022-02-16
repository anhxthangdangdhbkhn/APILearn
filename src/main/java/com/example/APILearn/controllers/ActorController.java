package com.example.APILearn.controllers;


import com.example.APILearn.models.Actor;
import com.example.APILearn.models.ResponseObject;
import com.example.APILearn.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/api/v1/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllActors(){
        List<Actor> actorLists = actorRepository.findAll();
        if(actorLists.size() >0){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Get all user suc",actorLists)
            );
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","data empty","")
            );
        }

    }

    //localhost:8080/api/v1/actors/page/10
    @RequestMapping(value = "/page/{page}", method = GET)
    @ResponseBody
    ResponseEntity<ResponseObject> getActorPageNo (@PathVariable int page) {
        Pageable pageable = PageRequest.of(page,5);
        Page<Actor> actorPage = actorRepository.findAll(pageable);
        if(!actorPage.isEmpty()){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Query actor OK",actorPage)
                    );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot find list  actor with pageNo= "+ page ,"")
            );
        }

    }

    //localhost:8080/api/v1/actors/id/1/firstName/12
    @RequestMapping(value = "/id/{id}/firstName/{firstName}", method = GET)
    @ResponseBody
    public String getFoosBySimplePathWithPathVariables
            (@PathVariable int id, @PathVariable int firstName) {
        return "Get a specific Bar with id=" + id +
                " from a Foo with id=" + firstName;
    }

    @GetMapping("/id/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Integer id){
        Optional<Actor> foundActor = actorRepository.findById(id);
        if(foundActor.isPresent()){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Query actor OK",foundActor)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot find actor with id= "+id ,"")
            );
        }

    }


    // localhost:8080/api/v1/actors/firstName/Penelope
    @GetMapping("/firstname/find/{find}")
    ResponseEntity<ResponseObject> findByFirstName(@PathVariable String find){
        List<Actor> actorList = actorRepository.findByFirstName(find);
        if(actorList.size()>0){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Query actor OK with firstName= " + find,actorList)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot find actor with firstname= "+find ,"")
            );
        }
    }

    // find fist name by keyword
    @GetMapping("/firstname/search/{search}")
    ResponseEntity<ResponseObject> searchFirstName(@PathVariable String search){
        List<Actor> actorList = actorRepository.searchFirstName(search);
        if(actorList.size()>0){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Query actor with search OK",actorList)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot search actor with firstname= "+search ,"")
            );
        }

    }

    @GetMapping("/lastname/find/{find}")
    ResponseEntity<ResponseObject> findByLastName(@PathVariable String find){
        List<Actor> actorList = actorRepository.findByLastName(find);
        if(actorList.size()>0){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Query actor OK with LastName= " + find,actorList)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot find actor with LastName= "+find ,"")
            );
        }
    }

    @GetMapping("/lastname/search/{search}")
    ResponseEntity<ResponseObject> searchLastName(@PathVariable String search){
        List<Actor> actorList = actorRepository.searchLastName(search);
        if(actorList.size()>0){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Query actor with search OK",actorList)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot search actor with lastName= "+search ,"")
            );
        }

    }




    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertActor(@RequestBody Actor newActor){

//        List<Actor> foundActor = actorRepository.finByActorName(newActor.getLastName().trim());
//        if(foundActor.size()>0){
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ResponseObject("FAILED","Actor name already taken","")
//            );
//        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Insert actor Successfully",actorRepository.save(newActor))
        );
    }

    // update a user
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject>updateActor(@RequestBody Actor newActor,@PathVariable int id){
        Actor updateActor = actorRepository.findById(id)
                .map(actor -> {
                    actor.setFirstName(newActor.getFirstName());
                    actor.setLastName(newActor.getLastName());
                    return actorRepository.save(actor);
                 }).orElseGet(()->{
                    newActor.setActor_id(id);
                    return actorRepository.save(newActor);
                 });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Update actor Successfully",updateActor)
        );
    }

    // delete a user
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject>deleteActor(@PathVariable int id){
        boolean exists = actorRepository.existsById(id);
        System.out.println("Delete ID: " + id);
        if(exists){
            actorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Deleted","")
            );
        };
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED","Cannot find actor","")
        );
    }




}
