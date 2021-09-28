package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaSpesa {

    String idNotaSpesa;
    String nomeProdotto;
    Integer quantita;

    public NotaSpesa(String nomeProdotto, Integer quantita) {
        this.nomeProdotto = nomeProdotto;
        this.quantita = quantita;
    }
}
