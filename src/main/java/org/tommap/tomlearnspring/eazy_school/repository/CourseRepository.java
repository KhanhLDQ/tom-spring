package org.tommap.tomlearnspring.eazy_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.tomlearnspring.eazy_school.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByOrderByNameDesc();
    /*
    - the Asc keyword is optional as OrderBy, by default, sorts the results in the ascending order
     */
    List<Course> findByOrderByName();
}
