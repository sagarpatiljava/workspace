

public class Person {
private String name ;
private String address;
private Integer phone;



public Person(String name, String address, Integer phone) {
	super();
	this.name = name;
	this.address = address;
	this.phone = phone;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Integer getPhone() {
	return phone;
}
public void setPhone(Integer phone) {
	this.phone = phone;
}
@Override
public String toString() {
	return "Person [name=" + name + ", address=" + address + ", phone=" + phone + "]";
}



}
