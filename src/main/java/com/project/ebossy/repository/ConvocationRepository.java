package com.project.ebossy.repository;

import com.project.ebossy.model.Convocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvocationRepository extends JpaRepository<Convocation, Integer> {
}