package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CourseService {

    ArrayList<Course> courses =new ArrayList<>();


    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    public boolean addCourse(Course course) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(course.getCourseCode())){
                return false;
            }
        }
        courses.add(course);
        return true;
    }




        public boolean updateCourse(Course  course, String courseCode) {

        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(course.getCourseCode())){
                courses.set(courses.indexOf(c), course);
                return true;            }

        }


        return false;

}


public boolean deleteCourse(String courseCode) {
    for (Course c : courses) {
        if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
            courses.remove(c);
            return true;
        }
    }
    return false;
}




// return all active courses based on the availability of instructor
public  ArrayList<Course> unActive(){

        ArrayList<Course> unActiveCourses = new ArrayList<>();
        for(Course c :courses){
            if (c.getInstructorName()==null){
                c.setActiveState(false);
                unActiveCourses.add(c);
            }
        }
    return unActiveCourses;
}








}
