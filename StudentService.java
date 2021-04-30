package com.example.demo.student;

//import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;
import java.util.List;
// service layer gives back data to the Student controller or the Api layer

@Service // to tell that this class is a spring Bean , Service class
public class StudentService {

    private   StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    // find by email
    public  void addNewStudent(Student student)  {
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());

        // checking if email is there
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
            studentRepository.save(student); // saving the studnet in the database



    };
    // get all students
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    //*************************  Delete  *****************************
    // localhost/8080/api/v1/student/1 to delete
    public void deleteStudent(long studentId) {
        //studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId + "does not exists");
        }
        studentRepository.deleteById(studentId);

    }

    //********************** Put **************************************

    @Transactional // entity goes into mange state
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "Studnet with id" + studentId + "does not exist" ));

        // checking if the name is not there so we can setthe name
        // we check if the name is not null and if its actaully greater than 0 , Object! is basically saying if the name provided by the user is not the
        // the same as the an already existing one , then set the name
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        // same thing like the name
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email is already taken");
            }
            student.setEmail(email);
        }
    }
}


