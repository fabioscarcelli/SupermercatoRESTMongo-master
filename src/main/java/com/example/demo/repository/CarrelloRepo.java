package com.example.demo.repository;

import com.example.demo.model.Carrello;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarrelloRepo extends MongoRepository<Carrello, String> {
}
