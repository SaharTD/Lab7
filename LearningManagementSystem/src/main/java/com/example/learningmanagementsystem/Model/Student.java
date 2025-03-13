package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {


    @NotEmpty(message = "id cant be empty ")
    @Size(max=8, message =" the id must contain 8 digits ")
    private String id;


    @NotEmpty(message = "name cant be empty ")
    @Size(min = 10, max=20 , message ="the user should be more then 8 character")
    @Pattern(regexp = "^[a-zA-Z ]+$", message ="The username should only contains letters")
    private String userName;


    @NotEmpty(message = "name cant be empty ")
    @Size(min = 8 , max=12 , message ="the password should be more then 8 character")
    @Pattern(regexp = "^[a-zA-Z ]+$", message ="The password should have letters only ")
    private String password;



//    @Pattern(regexp = "^[a-zA-Z ]{3}\\d{3}$", message = "the course code should be in the right format (MED202)")
    ArrayList<Course> courses;



    @AssertTrue // cuz student will have to add course, but they can delete later so in that case the student will be unActive
    private boolean activeState;

    @NotEmpty(message = "level  should not be empty")
    @Positive(message = "the level should be a positive number only ")
    private String level;






}
