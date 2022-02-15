package com.example.APILearn.repositories;


import com.example.APILearn.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// chua cac ham chua data
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    List<Actor> findByFirstName(String name);

    @Query(value= "SELECT * FROM public.actor WHERE first_name LIKE ?1%", nativeQuery = true)
    List<Actor> searchFirstName(String name);

    @Query(value= "SELECT * FROM public.actor WHERE last_name LIKE ?1%", nativeQuery = true)
    List<Actor> searchLastName(String name);
}
