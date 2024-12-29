package com.project.movie.requestDTO;

import com.project.movie.enums.Gender;

import lombok.Data;

@Data
public class UserRequest {
	private String name;
	private Integer age;
	private String address;
	private String mobileNo;
	private String emailId;
	private Gender gender;
	private String role;
	private String password;
}
