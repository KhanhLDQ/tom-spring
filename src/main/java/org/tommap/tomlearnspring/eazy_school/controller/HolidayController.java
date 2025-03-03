package org.tommap.tomlearnspring.eazy_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tommap.tomlearnspring.eazy_school.model.Holiday;

import java.util.Arrays;
import java.util.List;

@Controller
public class HolidayController {
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

        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
        );

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
