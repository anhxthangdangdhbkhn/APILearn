package com.example.APILearn.controllers;

import com.example.APILearn.models.Actor;
import com.example.APILearn.models.Country;
import com.example.APILearn.models.ResponseObject;
import com.example.APILearn.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("")
    List<Country> getAllCountries(){
        return  countryRepository.findAll();
    }

    @GetMapping("/list")
    List<Country> getPageCountries(){

        return  countryRepository.findAll();
    }

    @GetMapping("/{id}")
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


    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertActor(@RequestBody Country newCountry){

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Insert county Successfully",countryRepository.save(newCountry))
        );
    }

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



}
