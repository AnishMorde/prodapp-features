package com.example.anish.prodapp.controller;


import com.example.anish.prodapp.dto.LoginDto;
import com.example.anish.prodapp.dto.SignUpDto;
import com.example.anish.prodapp.dto.UserDto;
import com.example.anish.prodapp.service.AuthService;
import com.example.anish.prodapp.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity <UserDto>signUp(@RequestBody SignUpDto signUpDto){
        UserDto UserDto = userService.signUp(signUpDto);
         return ResponseEntity.ok(UserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto , HttpServletRequest request
                                         , HttpServletResponse response){
        String token = authService.login(loginDto);
        Cookie cookie = new Cookie("token" , token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return new ResponseEntity<>( token ,HttpStatus.OK  );
    }

}
