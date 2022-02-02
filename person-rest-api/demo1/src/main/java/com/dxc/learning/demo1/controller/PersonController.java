package com.dxc.learning.demo1.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dxc.learning.demo1.model.Person;
import com.dxc.learning.demo1.repository.PersonRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/person")
    public List<Person> all() {
        return repository.findAll();
    }

    @PostMapping("/person")
    public Person newPerson(@RequestBody Person newPerson) {
    
        return repository.save(newPerson);
    }

    @GetMapping("/person/{id}")
    public Person one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/person/{id}")
    public Person replacePerson(@RequestBody Person newPerson, @PathVariable Integer id) {

        return repository.findById(id)
                .map(Person -> {
                    Person.setName(newPerson.getName());
                    Person.setEmail(newPerson.getEmail());
        
                    return repository.save(Person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Integer id) {
        repository.deleteById(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult=ex.getBindingResult();
        Map<String,String> errors=new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName =((FieldError) error).getField();
            String errorMessage= error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("Message" , "validation failed");
        errors.put("status",HttpStatus.BAD_REQUEST.name());
        errors.put("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }
}