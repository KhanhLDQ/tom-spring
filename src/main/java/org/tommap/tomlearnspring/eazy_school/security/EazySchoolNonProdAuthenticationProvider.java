package org.tommap.tomlearnspring.eazy_school.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.tommap.tomlearnspring.eazy_school.model.Roles;
import org.tommap.tomlearnspring.eazy_school.repository.PersonRepository;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!prod")
public class EazySchoolNonProdAuthenticationProvider implements AuthenticationProvider {
    private final PersonRepository personRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        var person = personRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        return new UsernamePasswordAuthenticationToken(email, null, getGrantedAuthorities(person.getRoles()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
    }
}
