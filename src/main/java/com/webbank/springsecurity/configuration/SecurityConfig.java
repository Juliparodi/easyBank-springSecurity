package com.webbank.springsecurity.configuration;

import com.webbank.springsecurity.configuration.filter.CSRFCookieFilter;
import com.webbank.springsecurity.configuration.filter.JWTTokenFilter;
import com.webbank.springsecurity.configuration.filter.JWTTokenValidationFilter;
import com.webbank.springsecurity.model.AuthorityEnum;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
            .sessionManagement(sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(List.of("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }))
            .csrf(csrfCustomizer -> csrfCustomizer
                .csrfTokenRequestHandler(requestHandler)
                .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/register"), mvcMatcherBuilder.pattern("/contact"),new AntPathRequestMatcher("/h2-console/**"))
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            //.addFilterBefore(new RequestToVationBeforeFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new CSRFCookieFilter(), CsrfFilter.class)
            //    .addFilterAfter(new LoggingFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new JWTTokenFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(requests->requests
              //  .requestMatchers(mvcMatcherBuilder.pattern("/account")).hasAuthority(AuthorityEnum.VIEW_ACCOUNT.getName())
                //.requestMatchers(mvcMatcherBuilder.pattern("/balance")).hasAnyAuthority(AuthorityEnum.VIEW_ACCOUNT.getName(), AuthorityEnum.VIEW_BALANCE.getName())
                //.requestMatchers(mvcMatcherBuilder.pattern("/loans")).hasAnyAuthority(AuthorityEnum.VIEW_LOANS.getName(), AuthorityEnum.VIEW_BALANCE.getName())
                //.requestMatchers(mvcMatcherBuilder.pattern("/cards")).hasAnyAuthority(AuthorityEnum.VIEW_CARDS.getName(), AuthorityEnum.VIEW_BALANCE.getName())
                .requestMatchers(mvcMatcherBuilder.pattern("/account")).hasRole(AuthorityEnum.getUserRoleSubstring())
                .requestMatchers(mvcMatcherBuilder.pattern("/balance")).hasAnyRole(AuthorityEnum.getUserRoleSubstring(), AuthorityEnum.getAdminRoleSubstring())
               // .requestMatchers(mvcMatcherBuilder.pattern("/loans")).hasRole(AuthorityEnum.getUserRoleSubstring())
                .requestMatchers(mvcMatcherBuilder.pattern("/cards")).hasRole(AuthorityEnum.getUserRoleSubstring())
                .requestMatchers(mvcMatcherBuilder.pattern("/account"),
                    mvcMatcherBuilder.pattern("/contact"),
                    mvcMatcherBuilder.pattern("/balance"),
                    mvcMatcherBuilder.pattern("/loans"),
                    mvcMatcherBuilder.pattern("/cards"),
                    mvcMatcherBuilder.pattern("/user")).authenticated()
                .requestMatchers(mvcMatcherBuilder.pattern("/notices"),mvcMatcherBuilder.pattern("/register"),new AntPathRequestMatcher("/h2-console/**")).permitAll())
            .headers(headers -> headers
                .frameOptions(FrameOptionsConfig::sameOrigin))
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return PasswordEncoder
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


/**
 * Creates an JDBC user (with username and password) to interact with our app
 *
 * @return UserDetailsService Interface
 *
 *
 *  */
/*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

 */


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
