package com.paycontrol.service;

import com.paycontrol.exception.ResourceNotFoundException;
import com.paycontrol.model.PersonSignedPlan;
import com.paycontrol.repository.PersonSignedPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonSignedPlanService {

    @Autowired
    private PersonSignedPlanRepository personSignedPlanRepository;

    public List<PersonSignedPlan> findAllSignedPlan() {
        return personSignedPlanRepository.findAll();
    }

    public PersonSignedPlan save(final Long personSignedPlanId, final PersonSignedPlan personSignedPlan) {

        if (Optional.ofNullable(personSignedPlanId).isPresent()) {
            final PersonSignedPlan personSignedPlanExists = findById(personSignedPlanId);
            personSignedPlan.setId(personSignedPlanExists.getId());
        }

        List<PersonSignedPlan> allBySignedPlanId =
                personSignedPlanRepository.findAllBySignedPlanId(personSignedPlan.getSignedPlan().getId());

        if (allBySignedPlanId.isEmpty()) {
            personSignedPlan.setSequence(1);
        } else {
            final PersonSignedPlan personSignedPlan1 = allBySignedPlanId.get(0);
            personSignedPlan.setSequence(personSignedPlan1.getSequence() + 1);
        }

        return personSignedPlanRepository.save(personSignedPlan);
    }

    public PersonSignedPlan findById(Long personSignedPlanId) {
        return Optional.of(personSignedPlanRepository.findById(personSignedPlanId))
                .get().orElseThrow(
                        () -> new ResourceNotFoundException("PersonSignedPlan not found with id " + personSignedPlanId));
    }
}
