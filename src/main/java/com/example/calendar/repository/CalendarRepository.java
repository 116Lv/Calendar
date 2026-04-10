package com.example.calendar.repository;

import com.example.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Schedule 엔티티에 대한 데이터베이스 액세스 처리를 담당하는 레포지토리입니다.
 * JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공받습니다.
 */
@Repository
public interface CalendarRepository extends JpaRepository<Schedule, Long> {

}
