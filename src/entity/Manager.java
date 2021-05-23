package entity;

import java.util.Scanner;

import type.ManagerType;

public class Manager implements Cloneable{
	protected int age;
	protected String id;
	protected ManagerType jobPosition;
	protected String name;
	protected String password;
	protected String phoneNumber;
	protected Scanner sc;
	
	public Manager() {
		this.sc = new Scanner(System.in);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ManagerType getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(ManagerType jobPosition) {
		this.jobPosition = jobPosition;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Manager clone() {
		try {
			return (Manager)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}