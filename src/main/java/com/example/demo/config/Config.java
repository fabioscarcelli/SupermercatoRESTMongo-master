package com.example.demo.config;

import com.example.demo.model.Carrello;
import com.example.demo.model.Prodotto;
import com.example.demo.repository.CarrelloRepo;
import com.example.demo.repository.ProdottoRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(ProdottoRepo prodottoRepo, CarrelloRepo carrelloRepo) {
        return args -> {
            Prodotto prodotto1 = new Prodotto("Mela", 10.5);
            Prodotto prodotto2 = new Prodotto("Pera", 3.5);

            Prodotto prodotto3 = new Prodotto("Prosciutto", 20.98);
            Prodotto prodotto4 = new Prodotto("Biscotti", 10.0);


            Carrello carrello1 = new Carrello();
            Carrello carrello2 = new Carrello();
            Carrello carrelloVecchio = new Carrello(LocalDate.of(2020, 05, 14));

            prodottoRepo.deleteAll();
            prodottoRepo.saveAll(List.of(prodotto1, prodotto2, prodotto3, prodotto4));


            carrelloRepo.deleteAll();
            carrelloRepo.saveAll(List.of(carrello1, carrello2, carrelloVecchio));

        };
    }
}
