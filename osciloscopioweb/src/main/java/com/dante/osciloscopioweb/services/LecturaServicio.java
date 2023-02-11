package com.dante.osciloscopioweb.services;

import com.dante.osciloscopioweb.models.Lectura;
import com.dante.osciloscopioweb.repositories.LecturaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LecturaServicio {
    @Autowired
    LecturaRepo repo;

    public Lectura getLecturaById(String id){
        try{
            Optional<Lectura> temp = repo.findById(id);
            return temp.isEmpty() ? null : temp.get();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    public Page<Lectura> getListaLecturas(Pageable page){
        return repo.findAll(page);
    }
    public List<Lectura> getListaLecturas(){
        return repo.findAll();
    }
    public Lectura saveLectura(Lectura lectura){
        try{
            return repo.save(lectura);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
