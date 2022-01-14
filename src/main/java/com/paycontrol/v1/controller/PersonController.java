package com.paycontrol.v1.controller;

import com.paycontrol.model.Person;
import com.paycontrol.service.PersonService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {

  private PersonService personService;
  private PersonValidation personValidation;

  @Autowired
  public PersonController(PersonService personService, PersonValidation personValidation) {
    this.personService = personService;
    this.personValidation = personValidation;
  }

  @GetMapping("/{personId}")
  public ResponseEntity<Person> findById(@PathVariable Long personId) {
    return new ResponseEntity<>(personService.findById(personId), null, HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<?> findAll() {
    return new ResponseEntity<>(personService.findAllPerson(), null, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<Person> save(@RequestBody @Valid Person person,
                                     BindingResult bindingResult) {
    personValidation.validatesFieldsByMessageType(bindingResult);

    return new ResponseEntity<>(
        personService.save(null, person), null, HttpStatus.CREATED);
  }

  @PutMapping("/{personId}")
  public ResponseEntity<Person> edit(@PathVariable Long personId,
                                @RequestBody @Valid Person person) {

    return new ResponseEntity<>(
        personService.save(personId, person), null, HttpStatus.OK);
  }
}
