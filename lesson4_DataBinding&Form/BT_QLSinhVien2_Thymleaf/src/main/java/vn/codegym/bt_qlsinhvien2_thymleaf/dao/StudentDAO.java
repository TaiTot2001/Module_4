package vn.codegym.bt_qlsinhvien2_thymleaf.dao;

import vn.codegym.bt_qlsinhvien2_thymleaf.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/student_db";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    public StudentDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Lấy danh sách sinh viên
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getFloat("score")
                );
                students.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Thêm sinh viên mới
    public void save(Student student) {
        String sql = "INSERT INTO students (id, name, score) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setFloat(3, student.getScore());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Tìm theo id
    public Student findById(String id) {
        String sql = "SELECT id, name, score FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setScore(rs.getFloat("score"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, score = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setFloat(2, student.getScore());
            ps.setString(3, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}