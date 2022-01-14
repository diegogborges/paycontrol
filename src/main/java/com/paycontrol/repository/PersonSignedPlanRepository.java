package com.paycontrol.repository;

import com.paycontrol.model.PersonSignedPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonSignedPlanRepository extends JpaRepository<PersonSignedPlan, Long> {
}
