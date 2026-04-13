package com.example.calendar.repository;

import com.example.calendar.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Comment 엔티티의 scheduleId로 특정 일정의 총 댓글 수를 조회합니다.
     */
    long countByScheduleId(Long scheduleId);
}
