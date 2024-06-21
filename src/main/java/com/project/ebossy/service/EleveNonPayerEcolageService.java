package com.project.ebossy.service;

import com.project.ebossy.repository.EleveNonPayeEcolageRepository;
import com.project.ebossy.view.EleveNonPayeEcolage;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EleveNonPayerEcolageService {
    private final EleveNonPayeEcolageRepository ecoleNonPayerEcolageRepository;

    public EleveNonPayerEcolageService(EleveNonPayeEcolageRepository ecoleNonPayerEcolageRepository) {
        this.ecoleNonPayerEcolageRepository = ecoleNonPayerEcolageRepository;
    }

    public List<EleveNonPayeEcolage> getByEcole(Integer idEcole) {return ecoleNonPayerEcolageRepository.findAllByEcole(idEcole);}

    public List<EleveNonPayeEcolage> getByClasse(Integer idEcole,int idClasse) {return ecoleNonPayerEcolageRepository.findAllByClasse(idEcole,idClasse);}

}
