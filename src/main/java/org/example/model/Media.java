package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.model.enums.MediaType;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "media", schema = "media_schema")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "release_country")
    private String releaseCountry;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media_genres",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

}
