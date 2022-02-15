package com.example.APILearn.repositories;

import com.example.APILearn.models.Actor;
import com.example.APILearn.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address,Integer> {

    @Query(value= "SELECT address_id,address,address2,district,city.city_id,city.city, postal_code,phone,address.last_update FROM public.address\n" +
            "INNER JOIN public.city ON public.address.city_id = public.city.city_id;", nativeQuery = true)
    List<Address> findAllAddress();
}
