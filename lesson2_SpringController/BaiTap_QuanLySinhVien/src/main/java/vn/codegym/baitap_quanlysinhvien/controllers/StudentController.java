package vn.codegym.baitap_quanlysinhvien.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.baitap_quanlysinhvien.model.Student;
import vn.codegym.baitap_quanlysinhvien.services.StudentService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    // Spring sẽ inject StudentService từ @Bean AppConfig
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }



    @GetMapping("/students")
    public ModelAndView getStudents(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "dir", defaultValue = "asc") String dir,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        // 1. Lấy toàn bộ sinh viên
        List<Student> allStudents = studentService.findAll(q, sort, dir, page, size);

        // 2. Tìm kiếm (lọc theo q)
        if (q != null && !q.isEmpty()) {
            String keyword = q.toLowerCase();
            List<Student> filtered = new ArrayList<>();
            for (Student s : allStudents) {
                if (s.getId().toLowerCase().contains(keyword) ||
                        s.getName().toLowerCase().contains(keyword)) {
                    filtered.add(s);
                }
            }
            allStudents = filtered;
        }

        // 3. Sắp xếp
        Comparator<Student> comparator;
        switch (sort) {
            case "name": comparator = Comparator.comparing(Student::getName); break;
            case "score": comparator = Comparator.comparing(Student::getScore); break;
            default: comparator = Comparator.comparing(Student::getId); break;
        }
        if ("desc".equals(dir)) {
            comparator = comparator.reversed();
        }
        allStudents.sort(comparator);

        // 4. Tính tổng số trang
        int total = allStudents.size();
        int totalPages = (int) Math.ceil((double) total / size);

        // 5. Phân trang
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, total);
        List<Student> students = new ArrayList<>();
        if (fromIndex < toIndex) { // tránh lỗi IndexOutOfBounds
            students = allStudents.subList(fromIndex, toIndex);
        }

        // 6. Trả dữ liệu ra view
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("students", students);
        modelAndView.addObject("q", q);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("dir", dir);
        modelAndView.addObject("page", page);
        modelAndView.addObject("size", size);
        modelAndView.addObject("totalPages", totalPages);

        return modelAndView;
    }


    @GetMapping("/students/add")
    public String getAddStudentForm() {
        return "student-form";
    }

    @GetMapping("/students/detail")
    public ModelAndView getStudentDetail(@RequestParam("id") String id) {
        Student student = studentService.findById(id);
        ModelAndView mav = new ModelAndView("student-detail");
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("score") float score, RedirectAttributes redirectAttributes) {
        studentService.save(new Student(id, name, score));
        redirectAttributes.addFlashAttribute("message", "Bạn đã thêm sinh viên mới thành công");
        return "redirect:/students";
    }
}
