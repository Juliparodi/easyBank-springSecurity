package com.webbank.springsecurity.configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->
            requests.requestMatchers("/account", "/balance", "/loans", "/cards").authenticated()
                .requestMatchers("/notices", "contact").permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


/**
 * Creates an JDBC user (with username and password) to interact with our app
 *
 * @return UserDetailsService Interface
 *
 *
 *  */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


/**
 * Creates an inMemory user (with username and password) to interact with our app
 *
 * @return InMemoryUserDetailsManager with password encoded.
 */
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("1234"))
            .roles("admin")
            .build();

        UserDetails endUser = User.withUsername("endUser")
            .password(passwordEncoder.encode("1234"))
            .roles("read")
            .build();

        return new InMemoryUserDetailsManager(admin,endUser);
    }

     */

}
