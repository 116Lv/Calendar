package com.example.calendar.service;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;

import java.util.List;

/**
 * 일정 관리 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 * 인터페이스를 통해 구현체(ServiceImpl)를 분리하여 결합도를 낮추고 확장성을 높입니다.
 */
public interface CalendarService {

    /**
     * 새로운 일정을 생성하고 저장합니다.
     * @param dto : 등록할 일정 정보가 담긴 요청 DTO
     * @return 저장된 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    /**
     * 전체 일정을 조회합니다.
     * @return 전체 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    List<ScheduleResponseDto> getSchedules(String writerName);

    /**
     * 특정 일정을 조회합니다.
     * @param id : 검색할 일정의 id값
     * @return 요청한 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    ScheduleResponseDto getSchedule(Long id);

    /**
     * 특정 일정을 수정합니다.
     * @param id : 수정할 일정의 id값
     * @param dto : 수정할 일정 정보가 담긴 요청 DTO
     * @return 수정된 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    ScheduleResponseDto updateSchedule(Long id, String password, ScheduleRequestDto dto);

    /**
     * 특정 일정을 삭제합니다.
     * @param id : 삭제할 일정의 id값
     */
    void deleteSchedule(Long id, String password);
}
