package com.example.demo.model;


import lombok.Data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "Prodotto")
public class Prodotto {

    @Id
    String idProdotto;
    String nomeProdotto;
    Double prezzo;

    public Prodotto() {
    }

    public Prodotto(String idProdotto, String nomeProdotto, Double prezzo) {
        this.idProdotto = idProdotto;
        this.nomeProdotto = nomeProdotto;
        this.prezzo = prezzo;
    }

    public Prodotto(String nomeProdotto, Double prezzo) {
        this.nomeProdotto = nomeProdotto;
        this.prezzo = prezzo;
    }
}
