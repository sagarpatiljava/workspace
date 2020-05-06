package com.sagarnjava.net.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sagarnjava.net.dto.Employee;

@Service
public class EmployeeService {

	public Employee getEmployeeById(String  id) {
		System.out.println("Get employee by id");
		switch(id) {
		case"1" : return new Employee("1","sagar","Computer");
		case"2" : return new Employee("2","ashutosh","entc");
		case"3" : return new Employee("3","magar","it");
		default : return new Employee();
		}

	}
	
	public List<Employee> getAllEmployees() {
	
		return (List<Employee>) Arrays.asList(new Employee("1","sagar","Computer"),new Employee("2","ashutosh","entc"),
				new Employee("3","magar","it"));
		
	}
}
