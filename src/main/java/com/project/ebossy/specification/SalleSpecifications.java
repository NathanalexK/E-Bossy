package com.project.ebossy.specification;

import com.project.ebossy.model.Salle;
import org.springframework.data.jpa.domain.Specification;

public class SalleSpecifications {
    public static Specification<Salle> hasCapacityGreaterThan(int capacity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("capacity"), capacity);
    }

    public static Specification<Salle> hasCapacityLessThan(int capacity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("capacity"), capacity);
    }
}
