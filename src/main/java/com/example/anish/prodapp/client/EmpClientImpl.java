package com.example.anish.prodapp.client;

import com.example.anish.prodapp.advices.ApiResponse;
import com.example.anish.prodapp.dto.EmpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmpClientImpl implements  EmpClient {
    private final RestClient restClient;
    @Override
    public List<EmpDto> getAllEmployee() {
       try {
          ApiResponse< List<EmpDto>> empDtoList =  restClient.get()
                   .uri("employee/get")
                   .retrieve()
                   .body(new ParameterizedTypeReference<>() {
                   });
           return empDtoList.getData();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
