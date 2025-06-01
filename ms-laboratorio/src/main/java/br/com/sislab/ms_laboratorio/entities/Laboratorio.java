package br.com.sislab.ms_laboratorio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Integer capacity;
    private Integer computerCount;
    private String description;
    private boolean available;

    public Laboratorio(String name, String location, Integer capacity, Integer computerCount, String description, boolean available) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.computerCount = computerCount;
        this.description = description;
        this.available = available;
    }
}