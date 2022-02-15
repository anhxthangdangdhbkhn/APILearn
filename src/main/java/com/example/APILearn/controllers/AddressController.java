package com.example.APILearn.controllers;

import com.example.APILearn.models.Actor;
import com.example.APILearn.models.Address;
import com.example.APILearn.models.ResponseObject;
import com.example.APILearn.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllActors(){
        List<Address> addressList = addressRepository.findAllAddress();
        //List<Address> addressList = addressRepository.findAll();
        if(addressList.size() >0){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Get all Address OK",addressList)
            );
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FALSE","all Address is empty","")
            );
        }

    }
}
