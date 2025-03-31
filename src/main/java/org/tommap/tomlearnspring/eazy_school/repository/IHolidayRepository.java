package org.tommap.tomlearnspring.eazy_school.repository;

import org.tommap.tomlearnspring.eazy_school.model.Holiday;

import java.util.List;

public interface IHolidayRepository {
    List<Holiday> findAllHolidays();
}
