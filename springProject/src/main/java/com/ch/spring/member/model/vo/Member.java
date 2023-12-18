package com.ch.spring.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String userId;
	private String userPwd;
	private String userName;
	private String eamil;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private String endollDate;
	private String modifyDate;
	private String status;
}
