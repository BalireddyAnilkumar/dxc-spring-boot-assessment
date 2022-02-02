package com.dxc.learning.demo1.service;

import java.util.List;
import com.dxc.learning.demo1.model.Person;
import com.dxc.learning.demo1.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository PersonRepository;

    @Override
    public Person createPerson(Person Person) {
        return PersonRepository.save(Person);
    }

    @Override
    public void deletePerson(int id) {
        Person Person = getPersonId(id).orElseThrow();
        PersonRepository.delete(Person);
    }

    @Override
    public List<Person> getAllPerson() {

        return PersonRepository.findAll();
    }

    @Override
    public Person getPersonId(int id) {
        return PersonRepository.findById(id).orElseThrow();
    }

    @Override
    public Person updatePerson(int id) {
        return PersonRepository.getById(id);
    }

}