package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.AUGUST;
import static java.util.Calendar.SEPTEMBER;

@Configuration
public class StudentConfig {

    @Bean // will make it run , inject studentRepository so we can have access to it
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            // we are not going to ad id's, the database will do that for us
            Student mariam = new Student( "mariam Tariq " ,
                    LocalDate.of(2000, AUGUST,9),
                    "MariamTariq@gmail.com" );


            Student karim = new Student( "Karim Tariq ",
                    LocalDate.of(1996, SEPTEMBER,9),
                    "KarimTariq@gmail.com" );


            Student Alex = new Student( "Alex ",
                    LocalDate.of(1996, SEPTEMBER,9),
                    "Alex@gmail.com" );

// invoke them in to our repository or our Database
            repository.saveAll(
                    List.of(mariam,karim,Alex)
            );
        };

    };

}
