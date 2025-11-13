package vn.codegym.blog_application.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.codegym.blog_application.model.Post;
import vn.codegym.blog_application.model.User;
import vn.codegym.blog_application.repository.UserRepository;
import vn.codegym.blog_application.service.PostService;

import java.security.Principal;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/form";
    }



    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("post") Post post,
                       BindingResult result,
                       @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) return "posts/form";

        String username = userDetails.getUsername();
        User author = userRepo.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found: " + username)
        );

        if (post.getId() != null) {
            // Edit
            Post existing = postService.findById(post.getId());
            if (!existing.getAuthor().getUsername().equals(username)) {
                return "redirect:/access-denied";
            }
            existing.setTitle(post.getTitle());
            existing.setContent(post.getContent());
            postService.save(existing);
        } else {
            // Create
            post.setAuthor(author);
            postService.save(post);
        }

        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, Principal principal) {
        Post post = postService.findById(id);
        if (!post.getAuthor().getUsername().equals(principal.getName())) {
            return "access-denied";
        }
        model.addAttribute("post", post);
        return "posts/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.findById(id);
        if (!post.getAuthor().getUsername().equals(userDetails.getUsername())) {
            return "access-denied";
        }
        postService.delete(id, userDetails.getUsername());
        return "redirect:/posts";
    }


    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "posts/detail";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}





