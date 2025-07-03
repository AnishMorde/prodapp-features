package com.example.anish.prodapp.dto;



import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpDto {
    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDate dateJoined;
    private String role; //ADMIN , USER
    private Boolean isActive;

}

