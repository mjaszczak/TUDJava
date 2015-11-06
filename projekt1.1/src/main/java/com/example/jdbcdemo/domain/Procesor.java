package com.example.jdbcdemo.domain;

public class Procesor implements Comparable<Procesor>{
	
	private long id;
	private String name;
	private int serialNumber;
	private String frequency ;
	
	public Procesor(String name, int serialNumber, String frequency) {
		super();
		this.name = name;
		this.serialNumber = serialNumber;
		this.frequency = frequency;
	}
	public Procesor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Procesor(int i, String name1, int serialNumber1, String frequency1) {
		super();
		this.name = name1;
		this.serialNumber = serialNumber1;
		this.frequency = frequency1;
	}
	public long getId() {
		return id;
	}
	public void setId(long i) {
		this.id = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public int compareTo(Procesor compareArchive) {
		int compareSerialNumber = ((Procesor) compareArchive).getSerialNumber(); 
		return this.serialNumber - compareSerialNumber;
	}	
}
