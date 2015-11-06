package com.example.jdbcdemo.domain;

public class Laptop implements Comparable<Laptop>{
	
	private long id;
	private String brand;
	private String model;
	private int hdd;
	private int ram;
	private int serialNumber;
	
	public Laptop() {
		super();
	}
	public Laptop(String brand, String model, int hdd, int ram) {
		super();
		this.brand = brand;
		this.model = model;
		this.hdd = hdd;
		this.ram = ram;
	}
	public Laptop(long id, String brand, String model, int hdd, int ram) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.hdd = hdd;
		this.ram = ram;
	}
	public Laptop(String brand1, String model1, int hdd1, int ram1, int serialNumber1) {
		super();
		this.brand = brand1;
		this.model = model1;
		this.hdd = hdd1;
		this.ram = ram1;
		this.serialNumber = serialNumber1;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public int getHdd() {
		return hdd;
	}
	public void setHdd(int hdd) {
		this.hdd = hdd;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	
	public int compareTo(Laptop compareLaptop) {
		int compareHdd = ((Laptop) compareLaptop).getHdd(); 
		return this.hdd - compareHdd;
	}

}
