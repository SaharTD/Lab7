package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Instructor {



    @NotEmpty(message = "id cant be empty ")
    @Size(max=8, message =" the id must contain 8 digits ”")
    private String id;



    @NotEmpty (message = "“ the name cant be empty “")
    @Size (min = 10 , max=15 , message ="”ensure that the name length is at least 10 latters and at most 50 latters ”")
    @Pattern (regexp = "^[a-zA-Z ]+$", message ="The name should only contains letters" )
    private String name ;



    @NotEmpty(message = "user name cant be empty ")
    @Size(min = 8 , max=10 , message ="the user name  should be more then 8 character")
    @Pattern(regexp = "^[a-zA-Z ]+$", message ="The user name should only contains letters")
    private String userName;


    @NotEmpty(message = "name cant be empty ")
    @Size(min = 8 , max=20 , message ="the password should be more then 8 character")
    @Pattern(regexp = "^[a-zA-Z ]+$", message ="The password should have letters only ")
    private String password;

    ArrayList<Course> courses;


    @AssertFalse
    private boolean onLeave;


    @NotNull(message = "payCheck state should not be empty")
    @Positive(message = "the amount should be positive")
    private double payCheck;


    @NotNull(message = "the annual leaves should not be empty")
    @Positive(message = "the annual leaves should be a positive number or zero only ")
    private int annualLeaves;




}
