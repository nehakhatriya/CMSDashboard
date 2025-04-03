package com.cms.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.app.model.LoginRequest;

@RestController
@RequestMapping("app/auth")
public class LoginController {
	
	@PostMapping("/login")
	public String loginUser(LoginRequest loginRequest) {
		return "LoggedIn";
	}
}
