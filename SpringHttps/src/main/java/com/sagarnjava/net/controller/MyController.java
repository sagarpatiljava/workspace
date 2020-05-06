package com.sagarnjava.net.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/security")
public class MyController {

	@GetMapping
	public String getEmployee()
	{
		String msg="Hello World -Security : HTTPS";
		System.out.println(msg);
		return msg;
	}
}
