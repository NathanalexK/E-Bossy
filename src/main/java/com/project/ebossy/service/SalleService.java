package com.project.ebossy.service;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Classe;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Salle;
import com.project.ebossy.repository.ClasseRepository;
import com.project.ebossy.repository.SalleDisponibleRepository;
import com.project.ebossy.repository.SalleRepository;
import com.project.ebossy.view.SalleDisponible;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Service
public class SalleService {

    private final SalleRepository salleRepository;
    private final ClasseRepository classeRepository;
    private final SalleDisponibleRepository salleDisponibleRepository;

    public SalleService(SalleRepository salleRepository, ClasseRepository classeRepository,
                        SalleDisponibleRepository salleDisponibleRepository) {
        this.salleRepository = salleRepository;
        this.classeRepository = classeRepository;
        this.salleDisponibleRepository = salleDisponibleRepository;
    }

    public Salle findById(int id) {
        Optional<Salle> salle = salleRepository.findById(id);
        if (salle.isPresent()) {
            return salle.get();
        }
        return null;
    }

    public List<Salle> findAllByEcole(Ecole ecole) {
        return salleRepository.findAllByEcole(ecole.getId());
    }

    public List<Salle> findAllByIdEcole(Integer idEcole) {
        return salleRepository.findAllByEcole(idEcole);
    }

    public Salle save(Salle salle) {
        return salleRepository.save(salle);
    }

    public void delete(int idSalle) {
        salleRepository.deleteById(idSalle);
    }

    public List<SalleDisponible> getSalleDisponibles(Ecole ecole) {
        return salleDisponibleRepository.findByEcole(ecole.getId());
    }

    public List<Salle> findAllByAnneeScolaire(AnneeScolaire anneeScolaire) {
        return salleRepository.findAllByAnneeScolaire(anneeScolaire);
    }

//    public boolean isSalleOccupied(Salle salle) {}
}
