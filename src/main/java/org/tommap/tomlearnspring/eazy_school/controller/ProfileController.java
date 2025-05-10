package org.tommap.tomlearnspring.eazy_school.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tommap.tomlearnspring.eazy_school.model.Address;
import org.tommap.tomlearnspring.eazy_school.model.Person;
import org.tommap.tomlearnspring.eazy_school.model.Profile;
import org.tommap.tomlearnspring.eazy_school.repository.PersonRepository;

@Controller("tomProfileController")
@RequiredArgsConstructor
public class ProfileController {
    private final PersonRepository personRepository;

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession httpSession) {
        var person = (Person) httpSession.getAttribute("loggedInPerson");

        Profile profile = new Profile();

        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());

        if (null != person.getAddress()) {
            var address = person.getAddress();

            profile.setAddress1(address.getAddress1());
            profile.setAddress2(address.getAddress2());
            profile.setCity(address.getCity());
            profile.setState(address.getState());
            profile.setZipCode(address.getZipCode());
        }

        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);

        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession httpSession) {
        if (errors.hasErrors()) {
            return "profile.html";
        }

        var person = (Person) httpSession.getAttribute("loggedInPerson");

        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());

        if (null == person.getAddress()) {
            person.setAddress(new Address());
        }

        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        var savedPerson = personRepository.save(person);
        httpSession.setAttribute("loggedInPerson", savedPerson);

        return "redirect:/displayProfile";
    }
}
