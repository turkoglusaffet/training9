package com.spring.training9.test;

import org.springframework.web.client.RestTemplate;

import com.spring.training9.entity.TblUser;


public class RestTemplateTest {

	public static void main(String[] args) {
		String url = "http://localhost:8080/spring-application/mvc/userrest/";

		RestTemplate template = new RestTemplate();
		TblUser user = template.getForObject(url + 1, TblUser.class);
		System.out.println(user);
		user.setId(0);
		
		TblUser newUser = template.postForObject(url, user, TblUser.class);
		System.out.println(newUser);
	}

}
