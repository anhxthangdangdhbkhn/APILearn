package com.example.APILearn.controllers;

import com.example.APILearn.models.Actor;
import com.example.APILearn.models.City;
import com.example.APILearn.models.Country;
import com.example.APILearn.models.ResponseObject;
import com.example.APILearn.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/api/v1/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    //localhost:267/api/v1/countries
    //method GET
    @GetMapping("/all")
    ResponseEntity<ResponseObject> getAllCountries(){

        List<Country> countryList = countryRepository.findAll();
        if(countryList.size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Get all countries suc",countryList)
            );
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Data empty","")
            );
        }
    }

    //localhost:267/api/v1/countries/page/10
    //method GET
    @RequestMapping(value = "/page/{pageNo}", method = GET)
    @ResponseBody
    ResponseEntity<ResponseObject> getCountryPageNo(@PathVariable int pageNo){
        Pageable pageable = PageRequest.of(pageNo,5);
        Page<Country> countryPage = countryRepository.findAll(pageable);
        if(!countryPage.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Get page "+ pageNo,countryPage)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot find list  country with pageNo= "+ pageNo ,"")
            );
        }
    }


    //localhost:267/api/v1/countries/id/1
    //method GET
    @GetMapping("/id/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Integer id){
        Optional<Country> foundCountry = countryRepository.findById(id);
        if(foundCountry.isPresent()){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Query actor OK",foundCountry)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","Cannot find actor with id= "+id ,"")
            );
        }

    }

    //localhost:267/api/v1/countries/insert
//    {
//        "country":"Yne thanh",
//        "last_update": "2006-02-15T00:44:00.000+00:00"
//    }
    // method POST
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertActor(@RequestBody Country newCountry){

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Insert county Successfully",countryRepository.save(newCountry))
        );
    }

    //localhost:267/api/v1/countries/id
    // method DELETE
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject>deleteActor(@PathVariable int id){
        boolean exists = countryRepository.existsById(id);
        System.out.println("Delete ID: " + id);
        if(exists){
            countryRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Deleted","")
            );
        };
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED","Cannot find country","")
        );
    }

    //localhost:267/api/v1/countries/id
    // Method PUT
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateCountry(@RequestBody Country newCountry,@PathVariable int id){
        Country updateCountry = countryRepository.findById(id)
                .map(country -> {
                    country.setCountry(newCountry.getCountry());
                    return  countryRepository.save(country);
                }).orElseGet(()->{
                    newCountry.setCountry_id(id);
                    return countryRepository.save(newCountry);
                });
        return  ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Update actor Successfully",updateCountry)
        );

    }

}
