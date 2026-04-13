package com.example.calendar.repository;

import com.example.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Schedule 엔티티에 대한 데이터베이스 액세스 처리를 담당하는 레포지토리입니다.
 * JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공받습니다.
 */
@Repository
public interface CalendarRepository extends JpaRepository<Schedule, Long> {

    /**
     * 아이디(ID) 값을 기준으로 내림차순 정렬하여 전체 일정을 조회합니다.
     * 최신 등록된 아이디가 가장 먼저 나옵니다.
     */
    List<Schedule> findAllByOrderByEditDateDesc();

    /**
     * ID로 특정 일정을 조회합니다.
     * 결과가 null인 경우를 안전하게 대비하기 위해 Optional로 반환합니다.
     */
    Optional<Schedule> findById(Long id);
}
