package com.example.anish.prodapp.service;

import com.example.anish.prodapp.dto.Postdto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    List<Postdto> getAllPost();
    Postdto createPost(Postdto postdto);
    Postdto getPostById(Long id);
    Postdto updatePost(Postdto postdto , Long id);
}
