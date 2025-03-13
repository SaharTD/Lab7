package com.example.learningmanagementsystem.Controller;


import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Instructor;
import com.example.learningmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;



    @GetMapping("get")
    public ResponseEntity getCourses() {
        ArrayList<Course> courses = courseService.getAllCourses();
        return ResponseEntity.status(200).body(courses);
    }


    @PostMapping("add")
    public ResponseEntity addCourses(@RequestBody @Valid Course course, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isFound = courseService.addCourse(course);

        if (isFound) {
            return ResponseEntity.status(200).body(new ApiResponse("the course  : "+course.getCourseCode()+" is added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The course code is already found "));

    }


    @PutMapping("update/{courseCode}")
    public ResponseEntity updateInstructor(@PathVariable String courseCode, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if (courseService.updateCourse(course,courseCode)) {
            return ResponseEntity.status(200).body(new ApiResponse("the course  : "+course.getCourseCode()+" is updated successfully"));

        }


        return ResponseEntity.status(400).body(new ApiResponse("The course code is already found "));
    }


    @DeleteMapping("delete/{courseCode}")
    public ResponseEntity deleteCourse(@PathVariable String courseCode) {
        if (courseService.deleteCourse(courseCode)) {
            return ResponseEntity.status(200).body(new ApiResponse("the course  : "+courseCode+" is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The course code is not found "));

    }



    @GetMapping("getUnActiveCourses")
    public ResponseEntity getInActiveCourses() {
        ArrayList<Course> inActiveCourses = courseService.unActive();
        if(inActiveCourses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There arent any inactive courses"));

        }

        return ResponseEntity.status(200).body(inActiveCourses);
    }




}
