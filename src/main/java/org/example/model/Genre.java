package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.model.enums.GenreType;

import java.util.Set;

@Data
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany()
    private Set<Media> media;

}
