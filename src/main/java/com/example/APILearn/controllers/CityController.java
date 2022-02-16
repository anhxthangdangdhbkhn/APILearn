package com.example.APILearn.controllers;


import com.example.APILearn.models.*;
import com.example.APILearn.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/api/v1/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("all")
    ResponseEntity<ResponseObject> getAllCities(){
        List<City> cityList = cityRepository.findAll();
        if(cityList.size() >0){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Get all cities OK",cityList)
            );
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","get all city is empty","")
            );
        }

    }

    //localhost:8080/api/v1/cities/page/10
    //method GET
    @RequestMapping(value = "/page/{pageNo}", method = GET)
    @ResponseBody
    ResponseEntity<ResponseObject> getCityPageNo(@PathVariable int pageNo){
        Pageable pageable = PageRequest.of(pageNo,5);
        Page<City> cityPage = cityRepository.findAll(pageable);
        if(!cityPage.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Get page "+ pageNo,cityPage)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot find list  city with pageNo= "+ pageNo ,"")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertActor(@RequestBody City newCity){

        System.out.println("Country id " + newCity.getCountry().getCountry_id());
        System.out.println("new city " + newCity.toString());

//        List<Actor> foundActor = actorRepository.finByActorName(newActor.getLastName().trim());
//        if(foundActor.size()>0){
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ResponseObject("FAILED","Actor name already taken","")
//            );
//        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Insert actor Successfully",cityRepository.save(newCity))
        );
    }


}
