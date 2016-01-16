package com.example.projekt.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "laptop.wszystkie", query = "SELECT l FROM Laptop l")
})

public class Laptop implements java.io.Serializable{

    private Long id;
    private String firma;
    private String model;
    private Procesor procesor;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getFirma() {
        return firma;
    }
    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_procesor", nullable = false)
    public Procesor getProcesor() {
        return procesor;
    }
    public void setProcesor(Procesor procesor) { this.procesor = procesor; }


}
