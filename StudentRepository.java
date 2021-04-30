package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// this interface JPA reposirtory has all the methods needed for DAO
// this is the type the repository is going to work on which is student
@Repository // for data accesses
public interface StudentRepository extends JpaRepository<Student,Long > {
    // add students to our repository

//SELECT * FROM studnet where email is = what ever we pass
    //@Query("SELECT s FROM s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);




}
