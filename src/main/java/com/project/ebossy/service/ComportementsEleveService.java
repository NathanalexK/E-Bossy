package com.project.ebossy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ebossy.repository.ComportementsEleveRepository;
import com.project.ebossy.view.ComportementsEleve;


@Service
public class ComportementsEleveService {
    @Autowired
    private ComportementsEleveRepository comportementsEleveRepository;

    public ComportementsEleveService(ComportementsEleveRepository comportementsEleveRepository){
        this.comportementsEleveRepository = comportementsEleveRepository;
    }

    public List<ComportementsEleve> findAll(){
        return comportementsEleveRepository.findAll();
    }
}

