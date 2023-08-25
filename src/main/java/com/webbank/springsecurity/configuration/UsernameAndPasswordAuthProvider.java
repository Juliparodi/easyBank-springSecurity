package com.webbank.springsecurity.configuration;

import com.webbank.springsecurity.model.Authorities;
import com.webbank.springsecurity.model.Customer;
import com.webbank.springsecurity.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernameAndPasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<Customer> customer = customerRepository.findTopByEmail(username);
        if (customer.isPresent()) {
            if (passwordEncoder.matches(pwd, customer.get().getPwd())) {
                List<GrantedAuthority> authorities = getGrantedAuthorities(customer.get().getAuthorities());
                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details!");
        }    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authorities> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        authorities.forEach(value ->
            grantedAuthorities.add(new SimpleGrantedAuthority(value.getName()))
        );

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
