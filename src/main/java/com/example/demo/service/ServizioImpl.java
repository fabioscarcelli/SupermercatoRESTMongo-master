package com.example.demo.service;

import com.example.demo.model.Bilancio;
import com.example.demo.model.Carrello;
import com.example.demo.model.NotaSpesa;
import com.example.demo.model.Prodotto;
import com.example.demo.repository.CarrelloRepo;
import com.example.demo.repository.ProdottoRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServizioImpl implements Servizio {

    private final CarrelloRepo carrelloRepo;
    private final ProdottoRepo prodottoRepo;

    @Autowired
    public ServizioImpl(CarrelloRepo carrelloRepo, ProdottoRepo prodottoRepo) {
        this.carrelloRepo = carrelloRepo;
        this.prodottoRepo = prodottoRepo;
    }

    @Override
    public List<Prodotto> getProdotti() {
        return prodottoRepo.findAll();
    }

    @Override
    public Prodotto getProdottoById(String idProdotto) {
        return prodottoRepo.findById(idProdotto).get();
    }

    @Override
    public Prodotto getProdottoByNome(String nome) {
        return prodottoRepo.findProdottoByNomeProdotto(nome);
    }

    @Override
    public void saveProdotto(Prodotto prodotto) {
        prodottoRepo.save(prodotto);
    }

    @Override
    public void updateProdotto(Prodotto prodotto) {
        prodotto.setIdProdotto(prodottoRepo.findProdottoByNomeProdotto(prodotto.getNomeProdotto()).getIdProdotto());
        prodottoRepo.deleteById(prodottoRepo.findProdottoByNomeProdotto(prodotto.getNomeProdotto()).getIdProdotto());
        prodottoRepo.save(prodotto);
    }

    @Override
    public void deleteProdotto(String idProdotto) {
        prodottoRepo.deleteById(idProdotto);
    }

    @Override
    public List<Carrello> getCarrelli() {
        return carrelloRepo.findAll();
    }

    @Override
    public Carrello getCarrelloById(String idCarrello) {
        return carrelloRepo.findById(idCarrello).get();
    }

    @Override
    public void saveCarrello(Carrello carrello) {
        carrelloRepo.save(carrello);
    }

    @Override
    public void updateCarrello(Carrello carrello) {
        carrello.setListaCarrello(carrelloRepo.findById(carrello.getIdCarrello()).get().getListaCarrello());
        carrelloRepo.deleteById(carrello.getIdCarrello());
        carrelloRepo.save(carrello);
    }

    @Override
    public void deleteCarrello(String idCarrello) {
        carrelloRepo.deleteById(idCarrello);
    }

    @Override
    public List<Carrello> getCarrelliByAnno(Integer anno) {
        List<Carrello> temp = new ArrayList<Carrello>();
        for (Carrello carrello : getCarrelli()) {
            if (carrello.getData().getYear() == anno) {
                temp.add(carrello);
            }
        }
        return temp;
    }

    public void calcolaTotale(Carrello carrello) {
        List<NotaSpesa> temp = carrello.getListaCarrello();
        carrello.setTotaleCarrello(temp.stream().mapToDouble(p -> p.getQuantita() * (prodottoRepo.findProdottoByNomeProdotto(p.getNomeProdotto()).getPrezzo())).sum());
    }

    public Bilancio calcolaBilancio(Integer anno) {
        List<Carrello> listaBilancio = getCarrelliByAnno(anno);
        Double totaleBilancio = 0.0;

        for (Carrello carrello : listaBilancio) {
            totaleBilancio += carrello.getTotaleCarrello();
        }
        Bilancio bilancio = new Bilancio(listaBilancio.size(), totaleBilancio);
        return bilancio;
    }

}
