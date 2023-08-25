package com.webbank.springsecurity.repository;

import com.webbank.springsecurity.model.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value = "SELECT * FROM notice n WHERE CURRENT_DATE BETWEEN n.create_dt AND n.notic_end_dt", nativeQuery = true)
    List<Notice> findAllActiveNotices();

}
