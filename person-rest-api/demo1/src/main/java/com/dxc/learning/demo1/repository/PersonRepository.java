package com.dxc.learning.demo1.repository;

import com.dxc.learning.demo1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}