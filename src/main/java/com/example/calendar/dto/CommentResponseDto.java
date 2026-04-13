package com.example.calendar.dto;

import com.example.calendar.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 조회 요청에 대한 응답 데이터를 담는 DTO 클래스입니다.
 * 보안을 위해 엔티티의 필드 중 비밀번호(password)를 제외한 정보만 클라이언트에게 전달합니다.
 */
@Getter
public class CommentResponseDto {

    private Long id;                    // 댓글 고유 식별자(기본키)
    private Long scheduleId;            // 일정 고유 식별자
    private String commentContent;      // 댓글 내용
    private String writerName;          // 작성자명
    private LocalDateTime writeDate;    // 작성일
    private LocalDateTime editDate;     // 수정일

    /**
     * Entity 객체를 DTO 객체로 변환하기 위한 생성자입니다.
     * 호출 시 엔티티의 데이터를 필드에 복사하여 담습니다.
     *
     * @param comment : 변환할 댓글 엔티티 객체
     */
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.scheduleId = comment.getScheduleId();
        this.commentContent = comment.getCommentContent();
        this.writerName = comment.getWriterName();
        this.writeDate = comment.getWriteDate();
        this.editDate = comment.getEditDate();
    }
}
