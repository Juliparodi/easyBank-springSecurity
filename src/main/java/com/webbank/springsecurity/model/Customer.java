package com.webbank.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long customerId;

    private String email;
    private String name;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String pwd;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "customer_authority",
        joinColumns = @JoinColumn(name = "customerId"),
        inverseJoinColumns = @JoinColumn(name = "authorityId"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"customerId", "authorityId"})
    )
    private Set<Authorities> authorities = new HashSet<>();

    private LocalDate createDt;

}
