package org.tommap.tomlearnspring.eazy_school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants;
import org.tommap.tomlearnspring.eazy_school.model.Person;
import org.tommap.tomlearnspring.eazy_school.model.Roles;
import org.tommap.tomlearnspring.eazy_school.repository.PersonRepository;
import org.tommap.tomlearnspring.eazy_school.repository.RolesRepository;
import org.tommap.tomlearnspring.eazy_school.service.IPersonService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {
    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository;

    @Override
    public boolean createPerson(Person person) {
        boolean isSaved = false;

        Roles roles = rolesRepository.findByRoleName(EazySchoolConstants.STUDENT_ROLE)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        person.setRoles(roles);
        var savedPerson = personRepository.save(person);

        if (savedPerson.getPersonId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }
}
