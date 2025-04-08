package org.tommap.tomlearnspring.eazy_school.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tommap.tomlearnspring.eazy_school.model.Roles;
import org.tommap.tomlearnspring.eazy_school.repository.PersonRepository;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EazySchoolAuthenticationProvider implements AuthenticationProvider {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        var person = personRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (passwordEncoder.matches(pwd, person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(person.getName(), null, getGrantedAuthorities(person.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
    }
}
