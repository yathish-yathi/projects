package com.project.movie.responseDTO;

import com.project.movie.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private String name;
	private Integer age;
	private Gender gender;
	private String addres;
}
