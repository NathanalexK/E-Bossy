package com.project.ebossy.service;

import com.project.ebossy.repository.VEleveEcoleRepository;
import com.project.ebossy.view.VEleveEcole;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class VEleveEcoleService {
    private final VEleveEcoleRepository vEleveEcoleRepository;

    public VEleveEcoleService(VEleveEcoleRepository vEleveEcoleRepository){

        this.vEleveEcoleRepository = vEleveEcoleRepository;
    }
    public VEleveEcole getByIdEleve(Integer numero, Integer idclasse, Integer idEcole){
        Optional<VEleveEcole> e = vEleveEcoleRepository.findByNumero(numero,idclasse,idEcole);
        if (e.isPresent()){
            return e.get();
        }
        return null;
    }
}
