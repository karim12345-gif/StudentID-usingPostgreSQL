package com.example.demo.student;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;


// to map this to our Database postgres we use and to create a table as well
@Entity
@Table // table for database
public class Student {
    // this is how we map student to the table of the database
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "student_sequence"
    )
    private long id;
    private String name;
    @Transient // no need for the age to be  column in the database
    private Integer age;

    private LocalDate dateOfBirth;
    private String email;



    public Student(){

    }

    // without ID
    public Student(String name, LocalDate dateOfBirth, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    // with ID
    public Student(Long id, String name , LocalDate dateOfBirth, String email){
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }


    //Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        // gets the real age based of the year date and month
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                '}';
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

