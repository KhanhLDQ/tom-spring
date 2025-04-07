package org.tommap.tomlearnspring.eazy_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.tomlearnspring.eazy_school.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
