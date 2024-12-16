package com.yathish.jobListing.controller;

import com.yathish.jobListing.PostRepository;
import com.yathish.jobListing.model.Post;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping("/post")
    public Post post(@RequestBody Post post){
        return postRepository.save(post);
    }
}
