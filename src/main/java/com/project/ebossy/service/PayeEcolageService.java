package com.project.ebossy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ebossy.model.PayeEcolage;
import com.project.ebossy.repository.PayeEcolageRepository;

@Service
@Transactional
public class PayeEcolageService {

    private final PayeEcolageRepository payeEcolageRepository;

    public PayeEcolageService(PayeEcolageRepository payeEcolageRepository) {
        this.payeEcolageRepository = payeEcolageRepository;
    }

    public void save(PayeEcolage payeEcolage) {
        payeEcolageRepository.save(payeEcolage);
    }
}
