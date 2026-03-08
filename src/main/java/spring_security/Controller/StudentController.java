package spring_security.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import spring_security.model.Student;

@RestController
public class StudentController {
    private List<Student> students  = new ArrayList<>(List.of(
        new Student(1,"Hatim",70),
        new Student(3,"Jamila",70),
        new Student(9,"qutbu",50)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken)request.getAttribute("_csrf");
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

}

