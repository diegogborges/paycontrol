package com.paycontrol.repository;

import com.paycontrol.model.PersonSignedPlan;

import com.paycontrol.model.SignedPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonSignedPlanRepository extends JpaRepository<PersonSignedPlan, Long> {
    List<PersonSignedPlan> findPersonSignedPlanBySignedPlanIdOrderBySequenceAsc(final Long signedPlanId);

    List<PersonSignedPlan> findAllBySignedPlanId(final Long signedPlanId);

    List<PersonSignedPlan> findAllBySignedPlanIdAndSequence(final Long signedPlanId, final Integer sequence);

}
