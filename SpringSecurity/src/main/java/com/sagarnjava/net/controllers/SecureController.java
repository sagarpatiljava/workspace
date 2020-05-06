package com.sagarnjava.net.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/security")
public class SecureController {

	@GetMapping
	public String getEmployee()
	{
		String msg="Hello World -Security";
		System.out.println(msg);
		return msg;
	}
}
