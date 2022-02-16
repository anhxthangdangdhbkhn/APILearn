package com.example.APILearn.repositories;


import com.example.APILearn.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    @Query(value= "SELECT * FROM public.country", nativeQuery = true)
    List<Country> getAllCountries();
}
