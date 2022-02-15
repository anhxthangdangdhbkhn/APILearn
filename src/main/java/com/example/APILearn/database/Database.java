package com.example.APILearn.database;

import com.example.APILearn.models.Actor;
import com.example.APILearn.repositories.ActorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ngay khi ung dung duoc chay
@Configuration
public class Database {
    private static  final Logger logger = LoggerFactory.getLogger(Database.class);
    // insert mot vai ban ghi, neu chua co se tao bang
    @Bean
    CommandLineRunner initDatabase(ActorRepository actorRepository){


        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Actor actorA = new Actor("Dang","Duc Thang");
//                Actor actorB = new Actor("Vuong","Dinh Quy");
//                logger.info("Insert data "+ actorRepository.save(actorA));
//                logger.info("Insert data "+ actorRepository.save(actorB));
            }
        };
    }
}