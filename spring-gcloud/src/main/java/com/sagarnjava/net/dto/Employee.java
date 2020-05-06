package com.sagarnjava.net.dto;


public class Employee {

	private String empId;
	private String name;
	private String stream;
	
	public Employee() {
		
	}
	public Employee(String empId, String name, String stream) {
		super();
		this.empId = empId;
		this.name = name;
		this.stream = stream;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
