package com.example.calendar.dto;

import com.example.calendar.entity.Schedule;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * 일정 조회 요청에 대한 응답 데이터를 담는 DTO 클래스입니다.
 * 보안을 위해 엔티티의 필드 중 비밀번호(password)를 제외한 정보만 클라이언트에게 전달합니다.
 */
@Getter
public class ScheduleResponseDto {

    private Long id;                    // 일정 고유 식별자(기본키)
    private String title;               // 일정 제목
    private String content;             // 일정 내용
    private String writerName;          // 작성자명
    private LocalDateTime writeDate;    // 작성일
    private LocalDateTime editDate;     // 수정일

    /**
     * Entity 객체를 DTO 객체로 변환하기 위한 생성자입니다.
     * 호출 시 엔티티의 데이터를 필드에 복사하여 담습니다.
     *
     * @param schedule : 변환할 일정 엔티티 객체
     */
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writerName = schedule.getWriterName();
        this.writeDate = schedule.getWriteDate();
        this.editDate = schedule.getEditDate();
    }

}
