package com.sagarnjava.net.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagarnjava.net.dto.Employee;
import com.sagarnjava.net.service.EmployeeService;

@RestController
@RequestMapping("v1")
public class WebController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/Employees/{id}")
	public Employee getEmployee(@PathVariable("id") String id)
	{
		System.out.println("Request received.");
		
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/Employees")
	public List<Employee> getEmployee()
	{
		
		return employeeService.getAllEmployees();
	}
	
}
