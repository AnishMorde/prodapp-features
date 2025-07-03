package com.example.anish.prodapp;

import com.example.anish.prodapp.client.EmpClient;
import com.example.anish.prodapp.dto.EmpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProdappApplicationTests {

	@Autowired
	EmpClient empClient;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllEmp(){
		List<EmpDto> empDtoList = empClient.getAllEmployee();
		System.out.println(empDtoList);
	}

}
