package vn.codegym.bt_qlsinhvien2_thymleaf.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name ="students")
public class Student {

    @Id
    @NotBlank(message = "Mã số không được để trống")
    @Size(min = 3, max = 20, message = "Mã số phải từ 3–20 ký tự")
    private String id;

    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Tên chỉ được chứa chữ cái")
    private String name;

    @Min(value = 0, message = "Điểm tối thiểu là 0")
    @Max(value = 10, message = "Điểm tối đa là 10")
    private float score;

    String avatar; // lưu đường dẫn file

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Student() {
    }

    public Student(String id, String name, float score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getRank() {
        if (score >= 8) return "Giỏi";
        if (score >= 6.5) return "Khá";
        if (score >= 5) return "Trung bình";
        return "Yếu";
    }
}