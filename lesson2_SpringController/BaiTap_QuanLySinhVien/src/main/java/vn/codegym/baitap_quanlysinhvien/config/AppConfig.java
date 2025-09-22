package vn.codegym.baitap_quanlysinhvien.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.codegym.baitap_quanlysinhvien.services.StudentService;
import vn.codegym.baitap_quanlysinhvien.services.StudentServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public StudentService studentService() {
        return new StudentServiceImpl();
    }
}