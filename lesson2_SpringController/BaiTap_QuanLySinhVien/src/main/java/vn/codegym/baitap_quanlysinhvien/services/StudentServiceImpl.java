package vn.codegym.baitap_quanlysinhvien.services;

import vn.codegym.baitap_quanlysinhvien.dao.StudentDAO;
import vn.codegym.baitap_quanlysinhvien.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    public List<Student> findAll(String q, String sort, String dir, int page, int size) {
        List<Student> students = studentDAO.findAll();
        // 2. Tìm kiếm theo id hoặc name
        if (q != null && !q.isEmpty()) {
            String keyword = q.toLowerCase();
            List<Student> filtered = new ArrayList<>();

            for (Student s : students) {
                String idLower = s.getId().toLowerCase();
                String nameLower = s.getName().toLowerCase();

                if (idLower.contains(keyword) || nameLower.contains(keyword)) {
                    filtered.add(s);
                }
            }

            students = filtered;
        }

        // 3. Sắp xếp
        if (sort.equalsIgnoreCase("name")) {
            students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        } else if (sort.equalsIgnoreCase("score")) {
            students.sort((s1, s2) -> Float.compare(s1.getScore(), s2.getScore()));
        } else { // mặc định sort theo id
            students.sort((s1, s2) -> s1.getId().compareToIgnoreCase(s2.getId()));
        }

        // Nếu người dùng chọn DESC thì đảo ngược list
        if (dir.equalsIgnoreCase("desc")) {
            Collections.reverse(students);
        }

        // 4. Phân trang
        int fromIndex = (page - 1) * size;        // vị trí bắt đầu
        int toIndex = Math.min(fromIndex + size, students.size()); // vị trí kết thúc

        if (fromIndex >= students.size()) {
            return new ArrayList<>(); // trả về rỗng
        }

        return students.subList(fromIndex, toIndex);
    }

    @Override
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    public Student findById(String id) {
        return studentDAO.findById(id);
    }
}