package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Api Layer
@RestController// makes us serve rest class and the one we have is hello for now
@RequestMapping(path = "api/v1/student") // the URL path instead of localhost 8080 only

public class StudentController {
    private final StudentService studentService;
    //entiy design patterns

    //parameter
    @Autowired // so this reference of studentservice will be instantiate  for us and then injected into this
    // constructor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // in order for this to be served as an end point we have to annotate
    //methods handle the HTTP GET requests matched with given URI expression.
    @GetMapping
    public List<Student> getStudentsController(){
        return studentService.getStudents(); // we will get us the data of the Students

    };

    // post is used when you want to add new resources or data to the database
    @PostMapping
    public void registerNewStudents(@RequestBody Student student) {
        //Requestbody takes the reposnd from client side to student to database
        studentService.addNewStudent(student);
        // had an error here with static , but implemented this try and catch lets see if it works


    }

    // to delete using the ID
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") long studentId){
        studentService.deleteStudent(studentId);
    }

    //***Put** to set and change names and emails using id

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
            studentService.updateStudent(studentId,name,email);
            }

}




