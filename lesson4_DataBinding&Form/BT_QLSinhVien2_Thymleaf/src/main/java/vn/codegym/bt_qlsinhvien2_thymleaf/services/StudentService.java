package vn.codegym.bt_qlsinhvien2_thymleaf.services;

import vn.codegym.bt_qlsinhvien2_thymleaf.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll(String q, String sort, String dir, int page, int size);

    void save(Student student);

    Student findById(String id);

    void update(Student student);

    void delete(String id);
}
