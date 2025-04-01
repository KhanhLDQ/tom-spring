package org.tommap.tomlearnspring.eazy_school.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tommap.tomlearnspring.eazy_school.model.Holiday;

@Repository //spring data jpa will create the implementation of this interface at the runtime
public interface HolidayRepository extends CrudRepository<Holiday, String> {
}
