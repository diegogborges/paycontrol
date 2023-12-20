package com.paycontrol.v1.controller;

import com.paycontrol.model.Person;
import com.paycontrol.model.PersonSignedPlan;
import com.paycontrol.model.SignedPlan;
import com.paycontrol.service.PersonSignedPlanService;
import com.paycontrol.v1.model.request.PersonSignedPlanRequest;
import com.paycontrol.v1.validator.PersonSignedPlanValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/personSignedPlans")
public class PersonSignedPlanController {

    private final PersonSignedPlanService personSignedPlanService;
    private final PersonSignedPlanValidation personSignedPlanValidation;

    @GetMapping("/{signedPlanId}")
    public ResponseEntity<PersonSignedPlan> findById(@PathVariable Long personSignedPlanId) {
        return new ResponseEntity<>(personSignedPlanService.findById(personSignedPlanId), null, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(personSignedPlanService.findAllSignedPlan(), null, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PersonSignedPlan> save(@RequestBody @Valid PersonSignedPlanRequest personSignedPlanRequest,
                                       BindingResult bindingResult) {
        personSignedPlanValidation.validatesFieldsByMessageType(bindingResult);
        final PersonSignedPlan personSignedPlan = PersonSignedPlan.builder()
                .person(Person.builder().id(personSignedPlanRequest.getPersonId()).build())
                .signedPlan(SignedPlan.builder().id(personSignedPlanRequest.getSignedPlanId()).build())
                .initialDate(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(
                personSignedPlanService.save(null, personSignedPlan), null, HttpStatus.CREATED);
    }

    @PutMapping("/{signedPlanId}")
    public ResponseEntity<PersonSignedPlan> edit(@PathVariable Long signedPlanId,
                                       @RequestBody @Valid PersonSignedPlanRequest personSignedPlanRequest) {
        final PersonSignedPlan personSignedPlan = PersonSignedPlan.builder()
                .person(Person.builder().id(personSignedPlanRequest.getPersonId()).build())
                .signedPlan(SignedPlan.builder().id(personSignedPlanRequest.getSignedPlanId()).build())
                .initialDate(personSignedPlanRequest.getInitialDate())
                .sequence(personSignedPlanRequest.getSequence())
                .build();
        return new ResponseEntity<>(
                personSignedPlanService.save(signedPlanId, personSignedPlan), null, HttpStatus.OK);
    }
}

