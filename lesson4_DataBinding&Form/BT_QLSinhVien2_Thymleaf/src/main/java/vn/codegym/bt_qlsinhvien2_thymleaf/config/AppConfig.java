package vn.codegym.bt_qlsinhvien2_thymleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.codegym.bt_qlsinhvien2_thymleaf.services.StudentService;
import vn.codegym.bt_qlsinhvien2_thymleaf.services.StudentServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public StudentService studentService() {
        return new StudentServiceImpl();
    }
}