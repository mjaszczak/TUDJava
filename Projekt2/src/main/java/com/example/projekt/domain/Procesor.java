package com.example.projekt.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Procesor.wszystkie", query = "SELECT p FROM Procesor p"),
})

public class Procesor {
    private Long id;
    private String procesor;
    private String opis;

    private List<Laptop> laptopy = new ArrayList<Laptop>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "procesor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Laptop> getLaptopy() {
        return laptopy;
    }
    public void setLaptopy(List<Laptop> laptopy) {
        this.laptopy =  laptopy;
    }

    @Column(nullable = false)
    public String getProcesor() {
        return procesor;
    }
    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }

}
