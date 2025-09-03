package com.example.anish.prodapp.service;

import com.example.anish.prodapp.dto.LoginDto;
import com.example.anish.prodapp.dto.SignUpDto;
import com.example.anish.prodapp.dto.UserDto;
import com.example.anish.prodapp.entity.User;
import com.example.anish.prodapp.exception.ResourceNotFoundException;
import com.example.anish.prodapp.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(()-> new BadCredentialsException("User not found with this email : " + username));
    }

    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id" + id));
    }

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<User> user = userRepo.findByEmail(signUpDto.getEmail());
        if (user.isPresent()) {
            throw new BadCredentialsException("User with email already exists: " + signUpDto.getEmail());
        }

        User toCreate = modelMapper.map(signUpDto, User.class);
        toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));

        User savedUser = userRepo.save(toCreate);
        return modelMapper.map(savedUser, UserDto.class);
    }


}
