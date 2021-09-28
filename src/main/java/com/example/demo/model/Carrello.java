package com.example.demo.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.*;

@Data
@Document(collection = "Carrello")
public class Carrello {

    @Id
    String idCarrello;
    List<NotaSpesa> listaCarrello = new ArrayList<NotaSpesa>();
    Double totaleCarrello;
    LocalDate data;

    public Carrello() {
        this.data = LocalDate.now();
    }

    public Carrello(LocalDate data) {
        this.data = data;
    }

    public Carrello(List<NotaSpesa> listaCarrello) {
        this.listaCarrello = listaCarrello;
        this.data = LocalDate.now();
    }

    public Carrello(String idCarrello) {
        this.idCarrello = idCarrello;
        this.data = LocalDate.now();
    }

    public Carrello(String idCarrello, List<NotaSpesa> listaCarrello) {
        this.listaCarrello = listaCarrello;
        this.idCarrello = idCarrello;
        this.data = LocalDate.now();
    }
}
