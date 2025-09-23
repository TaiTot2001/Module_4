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
        List<Student> allStudents = studentService.findAll(q, sort, dir, 1, Integer.MAX_VALUE);

        // 2. Tìm kiếm
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
            case "name":
                comparator = Comparator.comparing(Student::getName);
                break;
            case "score":
                comparator = Comparator.comparing(Student::getScore);
                break;
            default:
                comparator = Comparator.comparing(Student::getId);
                break;
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

    @GetMapping("/students/edit")
    public ModelAndView getStudentEdit(@RequestParam("id") String id) {
        Student student = studentService.findById(id);
        ModelAndView mav = new ModelAndView("student-edit");
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/students/delete")
    public String deleteStudent(@RequestParam("id") String id,
                                RedirectAttributes redirectAttributes) {
        studentService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Đã xoá sinh viên " + id);
        return "redirect:/students";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("score") String scoreStr,
                             RedirectAttributes redirectAttributes) {

        boolean hasError = false;

        // --- Validate ID ---
        if (id == null || id.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorId", "Mã số không được để trống");
            hasError = true;
        } else if (id.length() < 3 || id.length() > 20) {
            redirectAttributes.addFlashAttribute("errorId", "Mã số phải từ 3–20 ký tự");
            hasError = true;
        } else if (studentService.findById(id) != null) {
            redirectAttributes.addFlashAttribute("errorId", "Mã số đã tồn tại");
            hasError = true;
        }

        if (name == null || name.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorName", "Tên không được để trống");
            hasError = true;
        } else {
            // Chuẩn hoá tên
            name = name.trim().replaceAll("\\s+", " ");
            if (!name.matches("^[\\p{L}\\s]+$")) {
                redirectAttributes.addFlashAttribute("errorName", "Tên chỉ được chứa chữ cái");
                hasError = true;
            }
        }

        float score = -1;
        try {
            score = Float.parseFloat(scoreStr);
            if (score < 0.0 || score > 10.0) {
                redirectAttributes.addFlashAttribute("errorScore", "Điểm phải trong khoảng 0–10");
                hasError = true;
            }
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("errorScore", "Điểm phải là số hợp lệ");
            hasError = true;
        }

        if (hasError) {
            redirectAttributes.addFlashAttribute("id", id);
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("score", scoreStr);
            return "redirect:/students/add";
        }

        studentService.save(new Student(id, name.trim(), score));
        redirectAttributes.addFlashAttribute("message", "Bạn đã thêm sinh viên mới thành công");
        return "redirect:/students";
    }

    @PostMapping("/students/edit")
    public String updateStudent(@RequestParam("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("score") String scoreStr,
                                RedirectAttributes redirectAttributes) {

        boolean hasError = false;

        // --- Validate Name ---
        if (name == null || name.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorName", "Tên không được để trống");
            hasError = true;
        } else {
            name = name.trim().replaceAll("\\s+", " ");
            if (!name.matches("^[\\p{L}\\s]+$")) {
                redirectAttributes.addFlashAttribute("errorName", "Tên chỉ được chứa chữ cái");
                hasError = true;
            }
        }

        // --- Validate Score ---
        float score = -1;
        try {
            score = Float.parseFloat(scoreStr);
            if (score < 0.0 || score > 10.0) {
                redirectAttributes.addFlashAttribute("errorScore", "Điểm phải trong khoảng 0–10");
                hasError = true;
            }
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("errorScore", "Điểm phải là số hợp lệ");
            hasError = true;
        }

        // Nếu có lỗi → quay lại form sửa
        if (hasError) {
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("score", scoreStr);
            return "redirect:/students/edit?id=" + id;
        }

        // Nếu hợp lệ → update
        Student student = new Student(id, name, score);
        studentService.update(student);

        redirectAttributes.addFlashAttribute("message", "Cập nhật sinh viên thành công");
        return "redirect:/students";
    }

    @PostMapping
    public String deleteStudent(@RequestParam("id") String id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}
