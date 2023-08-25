package com.webbank.springsecurity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private int noticeId;

    private String noticeSummary;

    private String noticeDetails;

    private LocalDate noticBegDt;

    private LocalDate noticEndDt;

    private LocalDate createDt;

    private LocalDate updateDt;

}
