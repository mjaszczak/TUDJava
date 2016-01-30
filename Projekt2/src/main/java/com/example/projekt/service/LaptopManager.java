package com.example.projekt.service;

import java.util.List;

import com.example.projekt.domain.Laptop;
import com.example.projekt.domain.Procesor;

public interface LaptopManager {

    Laptop pobierzLaptopPoId(Long id);
    Procesor pobierzProcesorPoId(Long id);

    List<Laptop> dajWszystkieLaptopy();
    List<Procesor> dajWszystkieProcesory();

    Long dodaj(Procesor procesor);
    Long dodaj(Laptop laptop);

    void edytuj(Laptop laptop);
    void edytuj(Procesor procesor);

    void usun(Procesor p);
    void usun(Laptop l);


    List<Laptop> wyszukajLaptopPoProcesorze(String szukanyProcesor);

    List<Laptop> wyszukajLaptop(Procesor procesor);


}
