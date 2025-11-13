package vn.codegym.blog_application.service;


import vn.codegym.blog_application.model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    Optional<User> findByUsername(String username);
}
