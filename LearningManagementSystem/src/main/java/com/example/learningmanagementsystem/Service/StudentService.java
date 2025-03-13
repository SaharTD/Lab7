package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();
    private final CourseService courseService1;


    public ArrayList<Student> getAllStudents() {
        return students;
    }


    public boolean addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(student.getId())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }


    public boolean updateStudent(Student student, String id) {

        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                students.set(students.indexOf(s), student);
                return true;
            }

        }
        return false;

    }


    public boolean delete(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                students.remove(s);
                return true;
            }
        }
        return false;
    }





    public boolean addCourse(Course course,String id) {
for (Student s : students) {
            if(s.getId().equalsIgnoreCase(id)){
            for (Course c:s.getCourses()) {
                if (c.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                    return false;
                    //course is already there
                }else {
s.getCourses().add(course);
courseService1.addCourse(course);
                    return true;
                }
            }
            }

        }
//no student found
return false ;
    }


// this method return all active courses
    public ArrayList<Course> getAlCourses(String stud_id) {

    ArrayList<Course>activeCourses=new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equalsIgnoreCase(stud_id)){
                for(Course c: students.get(i).getCourses()){
                    if(c.isActiveState()) {
                        activeCourses.add(c);
                    }


                }
                return  activeCourses;
            }

        }
        return null;
    }





    //if student did not enroll in any course then they are inactive
public String studentState(String id){
String flag;
        for(Student s : students){
            if(s.getId().equalsIgnoreCase(id)){
                for(Course c :s.getCourses()) {
                    if (!c.isActiveState()){
                        s.setActiveState(false);
                        return flag="false";
                    }
                }

            }
return flag="true";
        }
    return flag="notfound";

}












}

