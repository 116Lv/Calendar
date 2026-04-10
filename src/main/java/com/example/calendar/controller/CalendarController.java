package com.example.calendar.controller;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 일정 관리 관련 API 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor    // 어떤 매개변수를 받아도 그에 맞는 생성자 자동 생성
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * 일정 등록 API
     * @param dto : 클라이언트로부터 전달받은 일정 등록 정보 (JSON타입)
     * @return 등록 완료된 일정 정보와 상태 코드 201(Created) 반환
     */
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto dto) {

        // 비즈니스 로직은 서비스에서 하게 정보를 던져주고 결과만 받음
        ScheduleResponseDto writtenDto = calendarService.saveSchedule(dto);

        // 상태코드와 저장된 정보를 Body에 담아 반환
        return  ResponseEntity.status(HttpStatus.CREATED).body(writtenDto);
    }


}
