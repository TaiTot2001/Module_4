package vn.codegym.blog_application.service;

import vn.codegym.blog_application.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    Post findById(Long id);
    Post save(Post post);
    void delete(Long id, String username);
}