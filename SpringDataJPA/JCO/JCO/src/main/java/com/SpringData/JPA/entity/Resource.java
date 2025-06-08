package com.SpringData.JPA.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name = "resource_type")
// @DiscriminatorValue("R")
public class Resource {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer size;

    private String url;

    // @OneToOne(mappedBy = "resource")
    // private Lecture lecture;
}