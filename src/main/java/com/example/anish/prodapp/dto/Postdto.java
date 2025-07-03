package com.example.anish.prodapp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postdto {
    private Long id;
    private String title;
    private String description;
}
