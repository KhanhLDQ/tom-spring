package org.tommap.tomlearnspring.eazy_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.tomlearnspring.eazy_school.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
