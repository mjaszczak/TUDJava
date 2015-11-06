package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Procesor;

public class ProcesorManagerTest {
	ProcesorManager procesorManager = new ProcesorManager();
	
	private final static String NAME_1 = "i5";
	private final static int SERIALNUMBER_1 = 5;
	private final static String FREQUENCY_1 = "3500";
	
	private final static String NAME_2 = "i7";
	private final static int SERIALNUMBER_2 = 7;
	private final static String FREQUENCY_2 = "4000";
	
	private final static String NAME_3 = "i3";
	private final static int SERIALNUMBER_3 = 3;
	private final static String FREQUENCY_3 = "2300";
	
	@Before
	public void init(){
		Procesor procesor1 = new Procesor(NAME_1, SERIALNUMBER_1, FREQUENCY_1);
		Procesor procesor2 = new Procesor(NAME_2, SERIALNUMBER_2, FREQUENCY_2);
		procesorManager.clearProcesors();
		assertEquals(1, procesorManager.addProcesor(procesor1));
		assertEquals(1, procesorManager.addProcesor(procesor2));
		
	}
	
	@Test
	public void checkConnection(){
		assertNotNull(procesorManager.getConnection());
	}
	
	@Test
	public void checkAdding() {
		Set<Procesor> procesors = procesorManager.getAllProcesors();
		for (Procesor a : procesors) {
			if (a.getSerialNumber() == SERIALNUMBER_1) {
				assertEquals(NAME_1, a.getName());
				assertEquals(SERIALNUMBER_1, a.getSerialNumber());
				assertEquals(FREQUENCY_1, a.getFrequency());
			} else if (a.getSerialNumber() == SERIALNUMBER_2) {
				assertEquals(NAME_2, a.getName());
				assertEquals(SERIALNUMBER_2, a.getSerialNumber());
				assertEquals(FREQUENCY_2, a.getFrequency());
			}
		}
	}

	
	@Test
	public void checkReading(){
		Set<Procesor> procesors = procesorManager.getAllProcesors();
		assertEquals(2, procesors.size());
		for (Procesor a : procesors) {
			if (a.getSerialNumber() == SERIALNUMBER_1) {
				assertEquals(NAME_1, a.getName());
				assertEquals(SERIALNUMBER_1, a.getSerialNumber());
				assertEquals(FREQUENCY_1, a.getFrequency());
			} else if (a.getSerialNumber() == SERIALNUMBER_2) {
				assertEquals(NAME_2, a.getName());
				assertEquals(SERIALNUMBER_2, a.getSerialNumber());
				assertEquals(FREQUENCY_2, a.getFrequency());
			}
		}
	}

	@Test
	public void checkUpdating(){
		Procesor procesor3 = new Procesor(NAME_3, SERIALNUMBER_2, FREQUENCY_3);
		assertEquals(1, procesorManager.updateProcesor(procesor3));
		Set<Procesor> procesors = procesorManager.getAllProcesors();
		for (Procesor a : procesors) {
			if (a.getSerialNumber() == SERIALNUMBER_2) {
				assertEquals(NAME_3, a.getName());
				assertEquals(SERIALNUMBER_2, a.getSerialNumber());
				assertEquals(FREQUENCY_3, a.getFrequency());
			}
		}
	}

	@Test
	public void checkDeleting(){
		Procesor procesor3 = new Procesor(NAME_1, SERIALNUMBER_1, FREQUENCY_1);
		assertEquals(1, procesorManager.deleteProcesor(procesor3));
		
		Set<Procesor> procesors = procesorManager.getAllProcesors();
		
		assertEquals(1, procesors.size());
	}
	
	@Test
	public void checkSearching(){
		Procesor procesor3 = new Procesor(NAME_1, SERIALNUMBER_1, FREQUENCY_1);
		
		assertEquals(1, procesorManager.searchProcesor(procesor3));
		Set<Procesor> procesors = procesorManager.getAllProcesors();
		for (Procesor a : procesors) {
			if (a.getSerialNumber() == SERIALNUMBER_1) {
				assertEquals(NAME_1, a.getName());
				assertEquals(SERIALNUMBER_1, a.getSerialNumber());
				assertEquals(FREQUENCY_1, a.getFrequency());
			}
		}
	}

}
