package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController /// deal with json
@RequestMapping("/api/v1/student")/// open mapping
@RequiredArgsConstructor /// called when arg needed
public class StudentController {


    private final StudentService studentService;


    @GetMapping("get")
    public ResponseEntity getAllStudents() {
        ArrayList<Student> students = studentService.getAllStudents();
        return ResponseEntity.status(200).body(students);
    }


    @PostMapping("add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student,  Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isFound = studentService.addStudent(student);

        if (isFound) {
            return ResponseEntity.status(200).body(new ApiResponse("Student with id : "+student.getId()+" is added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The student id is already found "));

    }


    @PutMapping("update/{id}")
    public ResponseEntity updateStudents(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if (studentService.updateStudent(student, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Student with id : "+student.getId()+" is updated successfully"));

        }


        return ResponseEntity.status(400).body(new ApiResponse("The student id is already found "));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        if (studentService.delete(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Student with id : "+id+" is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The student id is not found "));

    }




    @PostMapping("addCourses/{id}")
    public ResponseEntity addCourse(@PathVariable String id ,@RequestBody @Valid Course course, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }


        if (!studentService.addCourse(course,id)) {
            return ResponseEntity.status(400).body(new ApiResponse("The course is already added "));
        }
        return ResponseEntity.status(200).body(new ApiResponse("The course is added successfully "));

    }



    @GetMapping("getCourses/{id}")
    public ResponseEntity getAllCourses(@PathVariable String id) {

        ArrayList<Course> courses = studentService.getAlCourses(id);
        if (courses==null){
            return ResponseEntity.status(400).body(new ApiResponse("The student is not found"));
        }
        return ResponseEntity.status(200).body(courses);
    }




    @GetMapping("getstate/{id}")
    public ResponseEntity getState(@PathVariable String id) {


        if (studentService.studentState(id)) {
            return ResponseEntity.status(200).body("the student state is updated ");

        }
            return ResponseEntity.status(200).body("the student is not found ");


    }

    

    @PutMapping("changeInstructor/{stud_id}/{courseCode}/{instructor_Name}")
    public ResponseEntity changeInstructor (@PathVariable String stud_id,@PathVariable String instructor_Name,@PathVariable String courseCode){

        if (studentService.changeInstructor(stud_id,instructor_Name,courseCode)){
            return ResponseEntity.status(200).body("Instructor has changed successfully  ");

        }
        return ResponseEntity.status(400).body("the student is not found ");


    }



}
