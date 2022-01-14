package com.paycontrol.service;

import com.paycontrol.exception.ResourceNotFoundException;
import com.paycontrol.model.Person;
import com.paycontrol.repository.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public List<Person> findAllPerson() {
    return personRepository.findAll();
  }

  public Person save(final Long personId, final Person person) {

    if (Optional.ofNullable(personId).isPresent()) {
          final Person personExists = findById(personId);
          person.setId(personExists.getId());
    }

    return personRepository.save(person);
  }

  public Person findById(Long personId) {
    return Optional.of(personRepository.findById(personId)).get()
        .orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + personId));
  }
}
