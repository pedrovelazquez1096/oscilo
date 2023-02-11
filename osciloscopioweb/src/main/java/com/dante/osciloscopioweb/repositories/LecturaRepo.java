package com.dante.osciloscopioweb.repositories;

import com.dante.osciloscopioweb.models.Lectura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturaRepo extends MongoRepository<Lectura, String> {
}
