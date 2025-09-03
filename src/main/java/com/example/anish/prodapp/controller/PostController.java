package com.example.anish.prodapp.controller;

import com.example.anish.prodapp.dto.Postdto;
import com.example.anish.prodapp.entity.User;
import com.example.anish.prodapp.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @PostMapping
    public Postdto createPost(@RequestBody Postdto postdto){
        return postService.createPost(postdto);
    }

    @GetMapping
    public List<Postdto> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public Postdto getPostById(@PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user info{}", user);
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public Postdto updatePost(@RequestBody Postdto postdto ,
                              @PathVariable Long id){
        return postService.updatePost(postdto ,id);
    }
}
