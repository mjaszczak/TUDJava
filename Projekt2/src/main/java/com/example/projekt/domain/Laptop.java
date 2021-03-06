package com.example.projekt.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "wyszukiwanie.obiekt", query = "SELECT l FROM Laptop l WHERE l.procesor LIKE :procesor"),
        @NamedQuery(name = "Wyszukiwanie.string", query = "SELECT l FROM Laptop l WHERE l.procesor.procesor LIKE :szukanyProcesor"),
        @NamedQuery(name = "laptop.wszystkie", query = "SELECT l FROM Laptop l")
})

public class Laptop implements java.io.Serializable{

    private Long id;
    private String nazwa;
    private Procesor procesor;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_procesor", nullable = false)
    public Procesor getProcesor() {
        return procesor;
    }
    public void setProcesor(Procesor procesor) { this.procesor = procesor; }



}
