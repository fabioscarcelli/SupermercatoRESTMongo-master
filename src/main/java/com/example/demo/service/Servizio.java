package com.example.demo.service;

import com.example.demo.model.Carrello;
import com.example.demo.model.Prodotto;

import java.util.List;

public interface Servizio {

    List<Prodotto> getProdotti();

    Prodotto getProdottoById(String idProdotto);

    Prodotto getProdottoByNome(String nome);

    void saveProdotto(Prodotto prodotto);

    void updateProdotto(Prodotto prodotto);

    void deleteProdotto(String idProdotto);


    List<Carrello> getCarrelli();

    Carrello getCarrelloById(String idCarrello);

    void saveCarrello(Carrello carrello);

    void updateCarrello(Carrello carrello);

    void deleteCarrello(String idCarrello);

    List<Carrello> getCarrelliByAnno(Integer anno);

}
