package com.example.demo.controller;

import com.example.demo.model.Bilancio;
import com.example.demo.model.Carrello;
import com.example.demo.model.NotaSpesa;
import com.example.demo.model.Prodotto;
import com.example.demo.service.ServizioImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/supermercato")
public class Controller {

    private final ServizioImpl servizio;

    @Autowired
    public Controller(ServizioImpl servizio) {
        this.servizio = servizio;
    }

    @GetMapping("/carrelli")
    public ResponseEntity<List<Carrello>> getCarrelli() {
        return ResponseEntity.ok().body(servizio.getCarrelli());
    }

    @GetMapping("/prodotti")
    public ResponseEntity<List<Prodotto>> getProdotti() {
        return ResponseEntity.ok().body(servizio.getProdotti());
    }

    @GetMapping("/carrelli/{idCarrello}")
    public ResponseEntity<Carrello> getCarrelloById(@PathVariable String idCarrello) {
        return ResponseEntity.ok().body(servizio.getCarrelloById(idCarrello));
    }

    @GetMapping("/prodotti/{idProdotto}")
    public ResponseEntity<Prodotto> getProdotttoById(@PathVariable String idProdotto) {
        return ResponseEntity.ok().body(servizio.getProdottoById(idProdotto));
    }

    @PostMapping("/prodotti")
    public ResponseEntity<Prodotto> addProdotto(@RequestBody Prodotto prodotto) {
        servizio.saveProdotto(prodotto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().
                path("/prodotti/ricerca/" + prodotto.getNomeProdotto()).toUriString());

        log.info("Prodotto {} salvato all'interno del database raggiungibile al link {} ", prodotto.getNomeProdotto(), uri.toString());
        return ResponseEntity.created(uri).body(prodotto);

    }

    @PostMapping("/carrelli")
    public ResponseEntity<Carrello> addCarrello(@RequestBody NotaSpesa notaSpesa) {
        Prodotto prodotto = servizio.getProdottoByNome(notaSpesa.getNomeProdotto());
        Carrello carrello = new Carrello();
        carrello.getListaCarrello().add(notaSpesa);
        servizio.calcolaTotale(carrello);
        servizio.saveCarrello(carrello);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().
                path("/carrelli/ricerca/" + carrello.getIdCarrello()).toUriString());

        log.info("Carrello {} salvato all'interno del database raggiungibile al link {} ", carrello.getIdCarrello(), uri.toString());
        return ResponseEntity.created(uri).body(carrello);
    }

    @GetMapping("/prodotti/ricerca/{nome}")
    public ResponseEntity<Prodotto> getProdottiByNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(servizio.getProdottoByNome(nome));
    }

    @PutMapping("/carrelli/addNewNota/{idCarrello}")
    public ResponseEntity<Carrello> addNota(@PathVariable String idCarrello, @RequestBody NotaSpesa notaSpesa) {
        Carrello carrello = servizio.getCarrelloById(idCarrello);
        if (carrello == null) {
            log.error("carrello non presente");
        } else {
            carrello.getListaCarrello().add(notaSpesa);
            servizio.calcolaTotale(carrello);
            servizio.deleteCarrello(idCarrello);
            servizio.saveCarrello(carrello);
        }
        return ResponseEntity.ok().body(carrello);
    }

    @GetMapping("/carrelli/ricerca")
    public ResponseEntity<Bilancio> getCarrelliByAnno(@RequestParam Integer anno) {
        Bilancio bilancio = servizio.calcolaBilancio(anno);
        return ResponseEntity.ok().body(bilancio);
    }
}
