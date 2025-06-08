package com.SpringData.JPA.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Course extends BaseEntity {

    private String name;

    private String description;

    @ManyToMany
    //Below annotation defines course(this) table is the owner of the relationship
    @JoinTable(
        name = "courses_authors",
        // Join columns for the Course entity (owner of the relationship)
        joinColumns = {
            @JoinColumn(name = "course_id")
        },
        // Join columns for the author entity (inverse side of the relationship)
        inverseJoinColumns = {
            @JoinColumn(name = "author_id")
        }
    )
    private List<Author> authors;
    
    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Section> sections;
}
