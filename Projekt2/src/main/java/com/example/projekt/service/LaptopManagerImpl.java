package com.example.projekt.service;


import com.example.projekt.domain.Laptop;
import com.example.projekt.domain.Procesor;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class LaptopManagerImpl implements LaptopManager {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSessionFactory() {
        return sf;
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Laptop> dajWszystkieLaptopy() {
        return sf.getCurrentSession().getNamedQuery("laptop.wszystkie").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Procesor> dajWszystkieProcesory() {
        return sf.getCurrentSession().getNamedQuery("Procesory.wszystkie").list();
    }

    @Override
    public List<Laptop> wyszukajLaptopPoProcesorze(String szukanyProcesor){
        return sf.getCurrentSession().getNamedQuery("wyszukiwanie.string").setString("szukanyProcesor",szukanyProcesor).list();
    }

    @Override
    public List<Laptop> wyszukajLaptop(Procesor procesor){
        return sf.getCurrentSession().getNamedQuery("wyszukiwanie.obiekt").setParameter("procesor",procesor).list();
    }

    @Override
    public Long dodaj(Laptop laptop) {
        Long id = (Long) sf.getCurrentSession().save(laptop);
        laptop.setId(id);
        Procesor procesor = (Procesor) sf.getCurrentSession().get(Procesor.class, laptop.getProcesor().getId());
        procesor.getLaptopy().add(laptop);
        return id;
    }

    @Override
    public Long dodaj(Procesor procesor) {
        Long id = (Long) sf.getCurrentSession().save(procesor);
        procesor.setId(id);
        return id;
    }


    @Override
    public void edytuj(Laptop laptop) {
        sf.getCurrentSession().update(laptop);
    }

    @Override
    public void edytuj(Procesor procesor) {
        sf.getCurrentSession().update(procesor);
    }

    @Override
    public Laptop pobierzLaptopPoId(Long id) {
        return (Laptop) sf.getCurrentSession().get(Laptop.class, id);
    }

    @Override
    public Procesor pobierzProcesorPoId(Long id) {
        return (Procesor) sf.getCurrentSession().get(Procesor.class, id);
    }

    @Override
    public void usun(Laptop laptop) {
        laptop = (Laptop) sf.getCurrentSession().get(Laptop.class, laptop.getId());
        Procesor procesor = (Procesor) sf.getCurrentSession().get(Procesor.class, laptop.getProcesor().getId());
        procesor.getLaptopy().remove(laptop);
        sf.getCurrentSession().delete(laptop);
    }

    @Override
    public void usun(Procesor procesor) {
        sf.getCurrentSession().delete( sf.getCurrentSession().get(Procesor.class, procesor.getId()) );
    }

}
