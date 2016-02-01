package com.example.projekt.service;


import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.example.projekt.domain.Procesor;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.projekt.domain.Laptop;
import org.junit.After;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class LaptopManagerTest {

    @Autowired
    LaptopManager manager;

    List<Long>  dodaneProcesory = new ArrayList<Long>();

    String procesor1 = "i5 5500u";
    String opisProcesora1 = "2x 2,4 ghz";

    String procesor2 = "i5 4400u";
    String opisProcesora2 = "2x 1,8 ghz";

    List<Long>  dodaneLaptopy = new ArrayList<Long>();

    String laptop1 = "HP probook";

    String laptop2 = "Asus N551";


    @Before
    public void sprawdzDodaneLaptopyProcesory() {

        List<Laptop> laptopy = manager.dajWszystkieLaptopy();
        List<Procesor> procesory = manager.dajWszystkieProcesory();

        for(Laptop laptop : laptopy)
            dodaneLaptopy.add(laptop.getId());

        for(Procesor procesor : procesory)
            dodaneProcesory.add(procesor.getId());
    }

    @After
    public void usunTestowaneWpisy() {

        List<Laptop> laptopy = manager.dajWszystkieLaptopy();

        List<Procesor> procesory = manager.dajWszystkieProcesory();

        boolean czyUsunac;

        for(Laptop laptop : laptopy) {
            czyUsunac = true;
            for (Long laptop2 : dodaneLaptopy)
                if (laptop.getId() == laptop2) {
                    czyUsunac = false;
                    break;
                }
            if(czyUsunac)
                manager.usun(laptop);
        }

        for(Procesor procesor : procesory) {
            czyUsunac = true;
            for (Long procesor2 : dodaneProcesory)
                if (procesor.getId() == procesor2)
                {
                    czyUsunac = false;
                    break;
                }
            if(czyUsunac)
                manager.usun(procesor);
        }
    }

    @Test
    public void Edycja() {

        Procesor proc = new Procesor();

        proc.setProcesor(procesor1);
        proc.setOpis(opisProcesora1);

        Laptop lap = new Laptop();

        lap.setProcesor(proc);
        lap.setNazwa(laptop1);


        Long procID = manager.dodaj(proc);
        Long lapID = manager.dodaj(lap);

        List<Laptop> laptopy = manager.dajWszystkieLaptopy();
        List<Procesor> procesory = manager.dajWszystkieProcesory();

        proc.setProcesor(procesor2);
        proc.setOpis(opisProcesora2);

        manager.edytuj(proc);

        lap.setProcesor(proc);
        lap.setNazwa(laptop2);
        manager.edytuj(lap);


        int a = 0;
        int b = 0;

        List<Laptop> laptopy2 = manager.dajWszystkieLaptopy();
        List<Procesor> procesory2 = manager.dajWszystkieProcesory();

        for(Laptop laptop : laptopy) {
            for (Laptop lap2 : laptopy2){
                if(laptop.getId() == lap2.getId()) {
                    if (laptop.getId() != lapID) {
                        assertEquals(lap2.getProcesor().getProcesor(), laptop.getProcesor().getProcesor());
                        assertEquals(lap2.getNazwa(), laptop.getNazwa());
                        a++;
                    } else if (laptop.getId() == lapID) {
                        assertEquals(procesor2, laptop.getProcesor().getProcesor());
                        assertEquals(opisProcesora2, laptop.getProcesor().getOpis());
                        assertEquals(laptop2, laptop.getNazwa());
                        b++;
                    }
                }
            }
        }
        assertEquals(b, 1);
        assertEquals(a+b, laptopy2.size());
        assertEquals(laptopy.size(), laptopy2.size());
        a = 0;
        b = 0;
        for(Procesor procesor : procesory) {
            for (Procesor proc2 : procesory2){
                if(procesor.getId() == proc2.getId()) {
                    if (procesor.getId() != procID) {
                        assertEquals(procesory2.get(a).getProcesor(), procesor.getProcesor());
                        assertEquals(procesory2.get(a).getOpis(), procesor.getOpis());
                        a++;
                    } else if (procesor.getId() == procID) {
                        assertEquals(procesor2, procesor.getProcesor());
                        assertEquals(opisProcesora2, procesor.getOpis());
                        b++;
                    }
                }
            }
        }

        assertEquals(b, 1);
        assertEquals(a+b, procesory2.size());
        assertEquals(procesory.size(), procesory2.size());
    }



    @Test
    public void Dodawanie() {

        Procesor procesor = new Procesor();

        procesor.setProcesor(procesor1);
        procesor.setOpis(opisProcesora1);

        Laptop laptop = new Laptop();

        laptop.setProcesor(procesor);
        laptop.setNazwa(laptop1);


        Long procesorId = manager.dodaj(procesor);
        Long laptopId = manager.dodaj(laptop);

        Laptop laptopPoId = manager.pobierzLaptopPoId(laptopId);
        Procesor procesorPoId = manager.pobierzProcesorPoId(procesorId);

       assertEquals(procesor1, procesorPoId.getProcesor());
        assertEquals(opisProcesora1, procesorPoId.getOpis());

        assertEquals(procesor1, laptopPoId.getProcesor().getProcesor());
        assertEquals(opisProcesora1, laptopPoId.getProcesor().getOpis());
       assertEquals(laptop1, laptopPoId.getNazwa());

    }

    @Test
    public void WyszukanieLaptopa(){


        Procesor proc = new Procesor();

        proc.setProcesor(procesor1);
        proc.setOpis(opisProcesora1);

        Procesor proc2 = new Procesor();

        proc2.setProcesor(procesor2);
        proc2.setOpis(opisProcesora2);

        manager.dodaj(proc);
        manager.dodaj(proc2);

        Laptop lap = new Laptop();

        lap.setProcesor(proc);
        lap.setNazwa(laptop1);

        Laptop lap2 = new Laptop();

        lap2.setProcesor(proc);
        lap2.setNazwa(laptop2);



        manager.dodaj(lap);
        manager.dodaj(lap2);

        assertEquals(manager.wyszukajLaptop(proc).size(), 2);

        for(Laptop laptop : manager.wyszukajLaptop(proc)) {
            assertEquals(proc.getId(), laptop.getProcesor().getId());
            assertEquals(proc.getProcesor(), laptop.getProcesor().getProcesor());
            assertEquals(proc.getOpis(), laptop.getProcesor().getOpis());
        }

        assertEquals(manager.wyszukajLaptop(proc2).size(), 0);

    }

    @Test
    public void UsunLaptopa(){

        int ile = manager.dajWszystkieLaptopy().size();

        Procesor proc = new Procesor();

        proc.setProcesor(procesor1);
        proc.setOpis(opisProcesora1);

        Laptop laptop = new Laptop();
        laptop.setProcesor(proc);
        laptop.setNazwa(laptop1);

        manager.dodaj(proc);
        Long lapID = manager.dodaj(laptop);

        assertEquals(manager.dajWszystkieLaptopy().size(), ile+1);

        manager.usun(laptop);
        assertEquals(manager.dajWszystkieLaptopy().size(), ile);

        Laptop lapPoId = manager.pobierzLaptopPoId(lapID);

        assertEquals(lapPoId, null);

    }

    @Test
    public void UsunProcesor(){

        int ile = manager.dajWszystkieProcesory().size();

        Procesor proc = new Procesor();

        proc.setProcesor(procesor1);
        proc.setOpis(opisProcesora1);

        Laptop laptop = new Laptop();
        laptop.setProcesor(proc);
        laptop.setNazwa(laptop1);

        long procID = manager.dodaj(proc);
        manager.dodaj(laptop);

        assertEquals(manager.dajWszystkieProcesory().size(), ile+1);

        manager.usun(proc);
        assertEquals(manager.dajWszystkieProcesory().size(), ile);

        Procesor procPoId = manager.pobierzProcesorPoId(procID);

        assertEquals(procPoId, null);


    }

    @Test
    public void UsunPowiazaneLaptopyZProcesorem(){

        int ileProcesorow = manager.dajWszystkieProcesory().size();
        int ileLaptopow = manager.dajWszystkieLaptopy().size();

        Procesor proc = new Procesor();

        proc.setProcesor(procesor1);
        proc.setOpis(opisProcesora1);

        Laptop laptop = new Laptop();
        laptop.setProcesor(proc);
        laptop.setNazwa(laptop1);

        manager.dodaj(proc);
        manager.dodaj(laptop);

        assertEquals(manager.dajWszystkieLaptopy().size(), ileLaptopow+1);
        assertEquals(manager.dajWszystkieProcesory().size(), ileProcesorow+1);

        manager.usun(proc);

        assertEquals(manager.dajWszystkieProcesory().size(), ileProcesorow);
        assertEquals(manager.dajWszystkieLaptopy().size(), ileLaptopow);



    }

    @Test
    public void PobraniePoID(){
        Procesor proc = new Procesor();

        proc.setProcesor(procesor1);
        proc.setOpis(opisProcesora1);

        Laptop lap = new Laptop();
        lap.setProcesor(proc);
        lap.setNazwa(laptop1);

        Long procesorID = manager.dodaj(proc);
        Long laptopID =manager.dodaj(lap);

        Laptop lap2 = manager.pobierzLaptopPoId(laptopID);

        assertEquals(laptopID, lap2.getId());

    }

    @Test
    public void PobieranieWszystkichProcesorow() {
        List<Procesor> procesory = manager.dajWszystkieProcesory();
        int ile = procesory.size();

        Procesor proc = new Procesor();

        proc.setProcesor(procesor1);
        proc.setOpis(opisProcesora1);

        manager.dodaj(proc);
        procesory = manager.dajWszystkieProcesory();
        assertEquals(ile+1, procesory.size());
    }

}

//insert into procesor values (1,'Core i5','4x 4ghz')
//insert into laptop values (1,'Toshiba',1)