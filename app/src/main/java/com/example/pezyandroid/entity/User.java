package com.example.pezyandroid.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pezy.pezy_api.enumerate.GenderEnum;
import com.pezy.pezy_api.enumerate.UserTypeEnum;

import lombok.Data;

@Data
public class User implements Serializable {


	public User(String username, String pwd){
		setUsername(username);
		setPassword(pwd);
	}

	private Long id;

	private String name;

	private String tel;

	private String email;

	private String username;

	private String password;

	private String token;
	
	@JsonProperty("profile_image")
	private String profileImage;

	@JsonProperty("user_type")
	private UserTypeEnum userType = UserTypeEnum.CUSTOMER;

	private GenderEnum gender = GenderEnum.MALE;

	@JsonProperty("birth_date")
	private Date birthDate;

	@JsonProperty("token_expire")
	private Date tokenExpire;

	@JsonProperty("create_uid")
	private Long createUID;

	@JsonProperty("update_uid")
	private Long updateUID;

	@JsonProperty("create_date")
	private Date createDate = new Date();

	@JsonProperty("update_date")
	private Date updateDate = new Date();

}
