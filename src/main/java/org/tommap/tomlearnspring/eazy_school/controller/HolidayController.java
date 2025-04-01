package org.tommap.tomlearnspring.eazy_school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tommap.tomlearnspring.eazy_school.model.Holiday;
import org.tommap.tomlearnspring.eazy_school.repository.HolidayRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayRepository holidayRepository;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(
            @PathVariable String display,
            Model model
    ) {
        boolean is_show_festival = false;
        boolean is_show_federal = false;

        if (null != display) {
            switch (display) {
                case "all" -> {
                    is_show_festival = true;
                    is_show_federal = true;
                }
                case "festival" -> is_show_festival = true;
                case "federal" -> is_show_federal = true;
            }
        }

        model.addAttribute("is_show_festival", is_show_festival);
        model.addAttribute("is_show_federal", is_show_federal);

        List<Holiday> holidays = StreamSupport.stream(
                holidayRepository.findAll().spliterator(), false
        ).toList();

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type: types) {
            model.addAttribute(
                    type.toString(),
                    holidays.stream()
                            .filter(holiday -> holiday.getType().equals(type))
                            .toList()
            );
        }

        return "holidays.html";
    }
}
