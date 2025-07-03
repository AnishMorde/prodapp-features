package com.example.anish.prodapp.service;

import com.example.anish.prodapp.dto.Postdto;
import com.example.anish.prodapp.entity.Post;
import com.example.anish.prodapp.exception.ResourceNotFoundException;
import com.example.anish.prodapp.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.crypto.spec.PSource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepo postRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<Postdto> getAllPost() {
       return postRepo
               .findAll()
               .stream()
               .map(postEntity -> modelMapper.map(postEntity , Postdto.class))
               .collect(Collectors.toList());


    }

    @Override
    public Postdto createPost(Postdto postdto) {
        Post post  = modelMapper.map(postdto , Post.class);
        postRepo.save(post);
        return  modelMapper.map(post , Postdto.class);
    }

    @Override
    public Postdto getPostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No such post found with this id : "+ id));
        return modelMapper.map(post , Postdto.class);
    }

    @Override
    public Postdto updatePost(Postdto inputPost , Long id) {

        Post olderpost = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not such a post Found with id : "+ id));
        inputPost.setId(id);
        //doing changes in the older post
        modelMapper.map(inputPost, olderpost);
        Post savedPost = postRepo.save(olderpost);
        return modelMapper.map(savedPost , Postdto.class);

      }
}
