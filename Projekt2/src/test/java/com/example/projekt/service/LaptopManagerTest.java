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

}