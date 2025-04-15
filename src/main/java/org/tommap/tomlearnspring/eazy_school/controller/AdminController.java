package org.tommap.tomlearnspring.eazy_school.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tommap.tomlearnspring.eazy_school.model.Course;
import org.tommap.tomlearnspring.eazy_school.model.EazyClass;
import org.tommap.tomlearnspring.eazy_school.model.Person;
import org.tommap.tomlearnspring.eazy_school.repository.CourseRepository;
import org.tommap.tomlearnspring.eazy_school.repository.EazyClassRepository;
import org.tommap.tomlearnspring.eazy_school.repository.PersonRepository;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final EazyClassRepository eazyClassRepository;
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;

    @GetMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        var eazyClasses = eazyClassRepository.findAll();

        var modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("eazyClass", new EazyClass());
        modelAndView.addObject("eazyClasses", eazyClasses);

        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(
            Model model,
            @ModelAttribute("eazyClass") EazyClass eazyClass
    ) {
        eazyClassRepository.save(eazyClass);
        return new ModelAndView("redirect:/admin/displayClasses");
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam(name = "id") int classId) {
        var eazyClass = eazyClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        for (Person person: eazyClass.getPersons()) {
            person.setEazyClass(null);
            personRepository.save(person);
        }

        eazyClassRepository.deleteById(classId);
        return new ModelAndView("redirect:/admin/displayClasses");
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(
            Model model,
            @RequestParam(name = "classId") int classId,
            HttpSession httpSession,
            @RequestParam(name = "error", required = false) String error
    ) {
        var eazyClass = eazyClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        ModelAndView modelAndView = new ModelAndView("students.html");
        modelAndView.addObject("eazyClass", eazyClass);
        modelAndView.addObject("person", new Person());

        httpSession.setAttribute("eazyClass", eazyClass);

        if ("true".equals(error)) {
            modelAndView.addObject("errorMsg", "Invalid Email entered!");
        }

        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(
            Model model,
            @ModelAttribute("person") Person personForm,
            HttpSession httpSession
    ) {
        var modelAndView = new ModelAndView();
        var eazyClass = (EazyClass) httpSession.getAttribute("eazyClass");
        var personOptional = personRepository.findByEmail(personForm.getEmail());

        if (personOptional.isEmpty()) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId() + "&error=true");
            return modelAndView;
        }

        var person = personOptional.get();

        person.setEazyClass(eazyClass);
        personRepository.save(person);

        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId());
        return modelAndView;
    }

    @RequestMapping("/deleteStudent")
    public ModelAndView deleteStudent(
            Model model,
            @RequestParam(name = "personId") int personId
    ) {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        var existingClassId = person.getEazyClass().getClassId();

        person.setEazyClass(null);
        personRepository.save(person);

        return new ModelAndView("redirect:/admin/displayStudents?classId=" + existingClassId);
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
//        var courses = courseRepository.findByOrderByName();
        var courses = courseRepository.findAll(Sort.by("name").descending());

        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("course", new Course());

        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Course course) {
        ModelAndView modelAndView = new ModelAndView();
        courseRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");

        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(
            Model model,
            @RequestParam(name = "id") int courseId,
            HttpSession httpSession,
            @RequestParam(name = "error", required = false) String error
    ) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        ModelAndView modelAndView = new ModelAndView("course_students.html");
        modelAndView.addObject("course", course);
        modelAndView.addObject("person", new Person());

        httpSession.setAttribute("course", course);

        if ("true".equals(error)) {
            modelAndView.addObject("errorMsg", "Invalid Email entered!");
        }

        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(
            Model model,
            @ModelAttribute("person") Person personForm,
            HttpSession httpSession
    ) {
        ModelAndView modelAndView = new ModelAndView();

        var course = (Course) httpSession.getAttribute("course");
        var personOptional = personRepository.findByEmail(personForm.getEmail());

        if (personOptional.isEmpty()) {
            modelAndView.setViewName("redirect:/admin/viewStudents?id=" + course.getCourseId() + "&error=true");
            return modelAndView;
        }

        //in JPA → only changes made on the owning side(the side with the foreign key) are persisted. Updating only the inverse side(the side with the mappedBy attribute) will not update the relationship in the database
        var person = personOptional.get();
        person.getCourses().add(course);
        personRepository.save(person);

        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + course.getCourseId());
        return modelAndView;
    }

    @RequestMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(
            Model model, @RequestParam(name = "personId") int personId, HttpSession httpSession
    ) {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        var course = (Course) httpSession.getAttribute("course");

        var filteredCourses = person.getCourses().stream()
                .filter(c -> c.getCourseId() != course.getCourseId())
                .collect(Collectors.toSet());

        person.setCourses(filteredCourses);
        personRepository.save(person);

        return new ModelAndView("redirect:/admin/viewStudents?id=" + course.getCourseId());
    }
}
