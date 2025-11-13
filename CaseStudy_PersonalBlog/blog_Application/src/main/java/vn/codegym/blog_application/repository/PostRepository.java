package vn.codegym.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.blog_application.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorUsername(String username);
}