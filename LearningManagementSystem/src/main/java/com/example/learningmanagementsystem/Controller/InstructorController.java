package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.Api.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Instructor;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {


    private final InstructorService instructorService;


    @GetMapping("get")
    public ResponseEntity getInstructor() {
        ArrayList<Instructor> instructors = instructorService.getAllInstructor();
        return ResponseEntity.status(200).body(instructors);
    }


    @PostMapping("add")
    public ResponseEntity addInstructor(@RequestBody @Valid Instructor instructor, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isFound = instructorService.addinstructor(instructor);

        if (isFound) {
            return ResponseEntity.status(200).body(new ApiResponse("instructor with id : "+instructor.getId()+" is added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The instructor id is already found "));

    }


    @PutMapping("update/{id}")
    public ResponseEntity updateInstructor(@PathVariable String id, @RequestBody @Valid Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if (instructorService.updateInstructors(instructor, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Instructor with id : "+instructor.getId()+" is updated successfully"));

        }


        return ResponseEntity.status(400).body(new ApiResponse("The instructor id is already found "));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteInstructor(@PathVariable String id) {
        if (instructorService.deleteInstructor(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Instructor with id : "+id+" is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The instructor id is not found "));

    }






    @PutMapping("requestLeave/{id}")
    public ResponseEntity requestLeave(@PathVariable String id ){
        String flag=instructorService.requestLeave(id);
        if (flag.equals("found")){
            return ResponseEntity.status(200).body(new ApiResponse("The requested vacation for the instructor with the id : "+id +" is approved "));

        }else if (flag.equals("zero")){
            return ResponseEntity.status(200).body(new ApiResponse("The requested vacation for the instructor with the id : "+id +" is not approved approved "));

        }
        else return ResponseEntity.status(400).body(new ApiResponse("The instructor id is not found "));

    }




    @PostMapping("assignCourses/{id}")
    public ResponseEntity addCourse(@PathVariable String id , @RequestBody @Valid Course course, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }


        if (!instructorService.assignCourse(course, id)) {
            return ResponseEntity.status(400).body(new ApiResponse("The course is already assigned "));
        }else return ResponseEntity.status(200).body(new ApiResponse("The course is assigned successfully "));

    }



    @GetMapping("getCourses/{name}")
    public ResponseEntity getAllCourses(@PathVariable String name) {

        ArrayList<Course> courses = instructorService.getAlCourses(name);
        if (courses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("The Instructor is not found"));
        }
        return ResponseEntity.status(200).body(courses);
    }






@PutMapping("requestRise/{id}")
    public ResponseEntity requestRise(@PathVariable String id){
        String flag=instructorService.requestRise(id);
        if(flag.equals("true")){
            return ResponseEntity.status(200).body(new ApiResponse("The rise request was approve"));

        }else if(flag.equals("notApproved")){
            return ResponseEntity.status(400).body(new ApiResponse("The request was not approved"));

        }else
    return ResponseEntity.status(400).body(new ApiResponse("The Instructor is not found"));

}









}
