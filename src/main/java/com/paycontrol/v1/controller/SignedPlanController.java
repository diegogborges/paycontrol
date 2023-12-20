package com.paycontrol.v1.controller;

import com.paycontrol.model.SignedPlan;
import com.paycontrol.service.SignedPlanService;
import com.paycontrol.v1.validator.SignedPlanValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/signedPlans")
public class SignedPlanController {

    private final SignedPlanService signedPlanService;
    private final SignedPlanValidation signedPlanValidation;

    @GetMapping("/{signedPlanId}")
    public ResponseEntity<SignedPlan> findById(@PathVariable Long signedPlanId) {
        return new ResponseEntity<>(signedPlanService.findById(signedPlanId), null, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(signedPlanService.findAllSignedPlan(), null, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SignedPlan> save(@RequestBody @Valid SignedPlan signedPlan,
                                       BindingResult bindingResult) {
        signedPlanValidation.validatesFieldsByMessageType(bindingResult);

        return new ResponseEntity<>(
                signedPlanService.save(null, signedPlan), null, HttpStatus.CREATED);
    }

    @PutMapping("/{signedPlanId}")
    public ResponseEntity<SignedPlan> edit(@PathVariable Long signedPlanId,
                                       @RequestBody @Valid SignedPlan signedPlan) {

        return new ResponseEntity<>(
                signedPlanService.save(signedPlanId, signedPlan), null, HttpStatus.OK);
    }
}

