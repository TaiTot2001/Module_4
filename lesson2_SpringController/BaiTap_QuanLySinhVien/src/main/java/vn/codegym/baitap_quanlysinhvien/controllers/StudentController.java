package vn.codegym.baitap_quanlysinhvien.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.baitap_quanlysinhvien.model.Student;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    public List<Student> students = new ArrayList<>();

    public StudentController() {
        // dữ liệu mẫu
        students.add(new Student("S001", "Nguyễn Văn A", 8.5f));
        students.add(new Student("S002", "Trần Thị B", 7.0f));
        students.add(new Student("S003", "Lê Văn C", 9.2f));
    }

    @GetMapping("/students")
    public ModelAndView getStudents() {
        ModelAndView mav = new ModelAndView("students");
        mav.addObject("students", students);
        return mav;
    }



    @GetMapping("/students/add")
    public String getAddStudentForm() {
        return "student-form";
    }

    @PostMapping("/students/add")
    public String addStudent(Student student) {
        students.add(student);
        return "redirect:/students";
    }

}
