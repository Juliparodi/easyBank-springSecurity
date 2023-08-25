package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.Authorities;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    Set<Authorities> findAllByName(String name);
    Authorities findByName(String name);

}
