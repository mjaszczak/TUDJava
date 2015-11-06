package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Procesor;
import com.example.jdbcdemo.domain.Laptop;

public class LaptopManagerTest {
	LaptopManager laptopManager = new LaptopManager();
	ProcesorManager procesorManager = new ProcesorManager();
	
	private final static String BRAND_1 = "Asus";
	private final static String MODEL_1 = "N551";
	private final static int HDD_1 = 512;
	private final static int RAM_1 = 8;
	private final static int SERIALNUMBER_1 = 5;
	
	private final static String BRAND_2 = "HP";
	private final static String MODEL_2 = "elitebook";
	private final static int HDD_2 = 1024;
	private final static int RAM_2 = 16;
	
	private final static String BRAND_3 = "Lenovo";
	private final static String MODEL_3 = "z50";
	private final static int HDD_3 = 128;
	private final static int RAM_3 = 4;
	
	private final static String NAMEPROCESOR_1 = "i5";
	private final static int SERIALNUMBERPROCESOR_1 = 5;
	private final static String FREQUENCYPROCESOR_1 = "3500";
	
	private final static String NAMEPROCESOR_2 = "i7";
	private final static int SERIALNUMBERPROCESOR_2 = 7;
	private final static String FREQUENCYPROCESOR_2 = "4000";
	private final static int SERIALNUMBERPROCESOR_3 = 3;
	
	@Before
	public void init(){
		Laptop laptop1 = new Laptop(BRAND_1, MODEL_1, HDD_1, RAM_1);
		Laptop laptop2 = new Laptop(BRAND_2, MODEL_2, HDD_2, RAM_2);
		Procesor procesor1 = new Procesor(NAMEPROCESOR_1, SERIALNUMBERPROCESOR_1, FREQUENCYPROCESOR_1);
		Procesor procesor2 = new Procesor(NAMEPROCESOR_2, SERIALNUMBERPROCESOR_2, FREQUENCYPROCESOR_2);
		laptopManager.clearLaptops();
		assertEquals(1, laptopManager.addLaptop(laptop1));
		assertEquals(1, laptopManager.addLaptop(laptop2));
		assertEquals(1, procesorManager.addProcesor(procesor1));
		assertEquals(1, procesorManager.addProcesor(procesor2));
	}
	
	@Test
	public void checkConnection(){
		assertNotNull(laptopManager.getConnection());
	}
   
	@Test
	public void checkAdding() {
		Set<Laptop> laptops = laptopManager.getAllLaptops();
		for (Laptop s : laptops) {
			if (s.getHdd() == HDD_1) {
				assertEquals(BRAND_1, s.getBrand());
				assertEquals(MODEL_1, s.getModel());
				assertEquals(HDD_1, s.getHdd());
				assertEquals(RAM_1, s.getRam());
			} else if (s.getHdd() == HDD_2) {
				assertEquals(BRAND_2, s.getBrand());
				assertEquals(MODEL_2, s.getModel());
				assertEquals(HDD_2, s.getHdd());
				assertEquals(RAM_2, s.getRam());
			}
		}
	}
	
	@Test
	public void checkAddingLaptopToProcesor(){
		Laptop laptop3 = new Laptop(BRAND_3, MODEL_3, HDD_3, RAM_3);
		Procesor procesor3 = new Procesor(NAMEPROCESOR_1, SERIALNUMBERPROCESOR_3, FREQUENCYPROCESOR_1);
		assertEquals(0, laptopManager.countRows(procesor3));
		assertEquals(1, laptopManager.addLaptopToProcesor(laptop3, procesor3));
		assertEquals(1, laptopManager.countRows(procesor3));
		Set<Laptop> laptops2 = laptopManager.getAllLaptops();
		for (Laptop s : laptops2) {
			if (s.getHdd() == HDD_3) {
				assertEquals(BRAND_3, s.getBrand());
				assertEquals(MODEL_3, s.getModel());
				assertEquals(HDD_3, s.getHdd());
				assertEquals(RAM_3, s.getRam());
				assertEquals(SERIALNUMBERPROCESOR_3, s.getSerialNumber());
			}
		}
	}
	
	@Test
	public void checkDeletingLaptopFromProcesor(){
		Laptop laptop3 = new Laptop(BRAND_3, MODEL_3, HDD_3, RAM_3);
		Procesor procesor3 = new Procesor(NAMEPROCESOR_1, SERIALNUMBERPROCESOR_3, FREQUENCYPROCESOR_1);
		assertEquals(0, laptopManager.countRows(procesor3));
		assertEquals(1, laptopManager.addLaptopToProcesor(laptop3, procesor3));
		assertEquals(1, laptopManager.countRows(procesor3));
		assertEquals(1, laptopManager.deleteLaptopFromProcesor(laptop3, procesor3));
		assertEquals(0, laptopManager.countRows(procesor3));
		Set<Laptop> laptops2 = laptopManager.getAllLaptops();
		for (Laptop s : laptops2) {
			if (s.getHdd() == HDD_3) {
				assertEquals(BRAND_3, s.getBrand());
				assertEquals(MODEL_3, s.getModel());
				assertEquals(HDD_3, s.getHdd());
				assertEquals(RAM_3, s.getRam());
				assertEquals(0, s.getSerialNumber());
			}
		}
		
	}
	/*
	@Test
	public void checkSelectingResourceFromArchive(){
		Resource resource2 = new Resource(NAME_2, AUTHOR_2, ISBN_2, DATE_2);
		Resource resource3 = new Resource(NAME_3, AUTHOR_3, ISBN_3, DATE_3);
		Archive archive3 = new Archive(NAMEARCHIVE_1, TEAMNUMBERARCHIVE_3, PHONEARCHIVE_1);
		resourceManager.clearResources();
		assertEquals(0, resourceManager.countRows(archive3));
		assertEquals(1, resourceManager.addResourceToArchive(resource3, archive3));
		assertEquals(1, resourceManager.addResourceToArchive(resource2, archive3));
		assertEquals(2, resourceManager.countRows(archive3));
		assertEquals(2, resourceManager.selectResourceFromArchive(archive3));
		
	}
	
	@Test
	public void checkSelectingResourceFromArchiveAfter(){
		Resource resource2 = new Resource(NAME_2, AUTHOR_2, ISBN_2, DATE_2);
		Resource resource3 = new Resource(NAME_3, AUTHOR_3, ISBN_3, DATE_3);
		Archive archive3 = new Archive(NAMEARCHIVE_1, TEAMNUMBERARCHIVE_3, PHONEARCHIVE_1);
		resourceManager.clearResources();
		assertEquals(0, resourceManager.countRows(archive3));
		assertEquals(1, resourceManager.addResourceToArchive(resource3, archive3));
		assertEquals(1, resourceManager.addResourceToArchive(resource2, archive3));
		assertEquals(2, resourceManager.countRows(archive3));
		assertEquals(2, resourceManager.selectResourceFromArchiveAfter(archive3, 1787));
		
	} */
}
