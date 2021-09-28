package com.example.demo.repository;

import com.example.demo.model.Prodotto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepo extends MongoRepository<Prodotto, String> {

    @Query("{'nomeProdotto' : ?0}")
    Prodotto findProdottoByNomeProdotto(String nomeProdotto);

}
