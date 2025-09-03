package com.example.anish.prodapp;

import com.example.anish.prodapp.client.EmpClient;
import com.example.anish.prodapp.dto.EmpDto;
import com.example.anish.prodapp.entity.User;
import com.example.anish.prodapp.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProdappApplicationTests {

	@Autowired
private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User(4L , "anishmorde@gmail" , "12345678");

		String token = jwtService.generateToken(user);
		System.out.println(token);

		Long id  = jwtService.getUserIdFromToken(token);
		System.out.println(id);
	}



}
