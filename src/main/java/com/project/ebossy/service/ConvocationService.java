package com.project.ebossy.service;

import com.project.ebossy.model.Convocation;
import com.project.ebossy.repository.ConvocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvocationService {
    private final ConvocationRepository convocationRepository;

    public ConvocationService(ConvocationRepository convocationRepository) {
        this.convocationRepository = convocationRepository;
    }


    public List<Convocation> getByClasse(Integer idEcole, int idClasse) {return convocationRepository.findAllByClasse(idEcole,idClasse);}



    public Convocation save(Convocation convocation){
        return convocationRepository.save(convocation);
    }
}
