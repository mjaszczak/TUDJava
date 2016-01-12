package com.example.projekt.domain;

import java.util.ArrayList;
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


}
