package com.yathish.jobListing.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "jobListing")
public class Post {
    private String profile;
    private String description;
    private String experience;
    private String[] technologies;
}
