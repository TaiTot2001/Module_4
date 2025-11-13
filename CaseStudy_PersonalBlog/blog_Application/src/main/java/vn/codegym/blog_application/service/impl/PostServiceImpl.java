package vn.codegym.blog_application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import vn.codegym.blog_application.model.Post;
import vn.codegym.blog_application.repository.PostRepository;
import vn.codegym.blog_application.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() { return postRepository.findAll(); }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    @Override
    public Post save(Post post) { return postRepository.save(post); }

    @Override
    public void delete(Long id, String username) {
        Post post = findById(id);
        if (!post.getAuthor().getUsername().equals(username))
            throw new AccessDeniedException("Only author can delete");
        postRepository.delete(post);
    }
}