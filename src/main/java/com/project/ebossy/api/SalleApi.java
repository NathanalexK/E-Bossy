package com.project.ebossy.api;


import com.project.ebossy.model.Salle;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.SalleService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/salle")
public class SalleApi {

    private final SalleService salleService;
    private final EcoleService ecoleService;

    public SalleApi(SalleService salleService, EcoleService ecoleService) {
        this.salleService = salleService;
        this.ecoleService = ecoleService;
    }

    @GetMapping("/all/{id}")
    public List<Salle> findAll(@PathVariable int id){
        return salleService.findAllByEcole(ecoleService.getEcoleById(2).get());
    }
}
