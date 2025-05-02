package org.tommap.tomlearnspring.eazy_school.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = "org.tommap.tomlearnspring.eazy_school.controller")
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView errorPage = new ModelAndView("error");
        errorPage.addObject("errorMsg", ex.getMessage());

        return errorPage;
    }
}
