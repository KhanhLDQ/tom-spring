package org.tommap.tomlearnspring.eazy_school.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tommap.tomlearnspring.eazy_school.model.Holiday;
import org.tommap.tomlearnspring.eazy_school.repository.IHolidayRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HolidayRepositoryImpl implements IHolidayRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Holiday> findAllHolidays() {
        String sql = "SELECT * FROM holidays";
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);

        return jdbcTemplate.query(sql, rowMapper);
    }
}
