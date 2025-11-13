package vn.codegym.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.blog_application.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

