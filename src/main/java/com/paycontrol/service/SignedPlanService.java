package com.paycontrol.service;

import com.paycontrol.exception.ResourceNotFoundException;
import com.paycontrol.model.SignedPlan;
import com.paycontrol.repository.SignedPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignedPlanService {

    @Autowired
    private SignedPlanRepository signedPlanRepository;

    public List<SignedPlan> findAllSignedPlan() {
        return signedPlanRepository.findAll();
    }

    public SignedPlan save(final Long signedPlanId, final SignedPlan signedPlan) {

        if (Optional.ofNullable(signedPlanId).isPresent()) {
            final SignedPlan signedPlanExists = findById(signedPlanId);
            signedPlan.setId(signedPlanExists.getId());
        }

        return signedPlanRepository.save(signedPlan);
    }

    public SignedPlan findById(Long signedPlanId) {
        return Optional.of(signedPlanRepository.findById(signedPlanId)).get()
                .orElseThrow(() -> new ResourceNotFoundException("SignedPlan not found with id " + signedPlanId));
    }
}
