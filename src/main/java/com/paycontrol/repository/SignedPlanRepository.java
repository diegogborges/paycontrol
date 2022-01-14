package com.paycontrol.repository;

import com.paycontrol.model.SignedPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignedPlanRepository extends JpaRepository<SignedPlan, Long> {
}
