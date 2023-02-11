package com.dante.osciloscopioweb.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lecturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lectura {
    @Id
    private String id;
    private float xvalue;
    private float yvalue;
}
