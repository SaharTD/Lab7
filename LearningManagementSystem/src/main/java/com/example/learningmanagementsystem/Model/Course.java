package com.example.learningmanagementsystem.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {



    @NotEmpty(message = "course cant be empty ")
    @Pattern(regexp = "^[a-zA-Z ]{3}\\d{3}$", message = "the course code should be in the right format (MED202)")
    private String courseCode;


    @NotEmpty(message = "instructor should not be empty")
    private String instructorName;

    @NotNull(message = "duration should not be empty")
    @PositiveOrZero(message = "the duration must be positive or zero")
    private int duration ;


    @AssertTrue //its true unless the instructor leaves or changes the assigned courses
    private boolean activeState;








}
