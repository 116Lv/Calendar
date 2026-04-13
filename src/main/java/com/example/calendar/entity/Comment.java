package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

/**
 * 댓글 정보를 저장하는 데이터베이스 매핑 엔티티입니다.
 * JPA Auditing을 사용하여 생성 및 수정 시간을 자동으로 관리합니다.
 */
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)  // 생성,수정 시간 자동 기록을 위한 리스너
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 Auto Increment
    private Long id;

    @Column(nullable = false)   // NOT NULL
    private Long scheduleId;

    @Column(columnDefinition = "TEXT")  // 긴 본문을 저장하기 위해 TEXT 타입으로 지정
    private String commentContent;

    @Column(nullable = false)
    private String writerName;

    @Column(nullable = false)
    private String password;

    @CreatedDate    // 엔티티 생성 시 시간이 자동으로 저장됨
    @Column(updatable = false)  // 생성일은 수정되지 않도록 설정
    private LocalDateTime writeDate;

    @LastModifiedDate   // 엔티티 수정 시 시간이 자동으로 업데이트됨
    private LocalDateTime editDate;
}
