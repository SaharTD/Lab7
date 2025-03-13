package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Instructor;
import com.example.learningmanagementsystem.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@RequiredArgsConstructor
@Service
public class InstructorService {


    ArrayList<Instructor> instructors =new ArrayList<>();
private final CourseService courseService1;



    public ArrayList<Instructor> getAllInstructor() {
        return instructors;
    }

    public boolean addinstructor(Instructor instructor) {
        for (Instructor s : instructors) {
            if (s.getId().equalsIgnoreCase(instructor.getId())){
                return false;
            }
        }
        instructors.add(instructor);
        return true;
    }




    public boolean updateInstructors(Instructor  instructor, String id) {

        for (Instructor s :instructors ) {
            if (s.getId().equalsIgnoreCase(id)) {
                instructors.set(instructors.indexOf(s), instructor);
                return true;
            }

        }
        return false;

    }


    public boolean deleteInstructor(String id) {
        for (Instructor s :instructors) {
            if (s.getId().equalsIgnoreCase(id)) {
                instructors.remove(s);
                return true;
            }
        }
        return false;
    }




    public String requestLeave(String id) {

        String flag;
        for (Instructor s :instructors ) {
            if (s.getId().equalsIgnoreCase(id)) {
                if(!s.isOnLeave()&& s.getAnnualLeaves()!=0){
                s.setOnLeave(true);
                s.setAnnualLeaves(s.getAnnualLeaves()-1);
                    for (Course c: s.getCourses()) {
                       c.setInstructorName(null);
                    }
                return flag="found" ;

                }
                return flag="zero";
            }

        }
        return flag="notfound";

    }





    public boolean assignCourse(Course course,String instructor_id) {
        for (Instructor s : instructors) {
            if(s.getId().equalsIgnoreCase(instructor_id)){
                for (Course c:s.getCourses()) {
                    if (c.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                        return false;
                        //course is already there
                    }
                }
                        s.getCourses().add(course);
                        courseService1.addCourse(course);
                        return true;

            }

        }
//no student found
        return false ;
    }







    public ArrayList<Course> getAlCourses(String instructor_name) {

        ArrayList<Course> activeCourses = new ArrayList<>();
        for (Instructor i : instructors) {
            if (i.getName().equalsIgnoreCase(instructor_name)){
            for (Course c : i.getCourses()) {
                        if (c.isActiveState()) {
                            //show the active state courses
                            activeCourses.add(c);
                        }

                    }
                    return activeCourses;
                }

            }

        return activeCourses;

    }

// if the instructor has 5 courses or more it will earn a rise

    public String requestRise(String id ){
        String flag;
        for (Instructor s : instructors){
            if (s.getId().equalsIgnoreCase(id)){
                if(s.getCourses().size()>=5){
                    s.setPayCheck(s.getPayCheck()+s.getPayCheck()*0.02);

                    return flag="true";
                }
                return flag="notApproved";
            }
        }
        return flag="false";
    }



    }







