package com.example.calendar.controller;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 전체 일정 조회 API
     * @return 전체 일정 정보와 상태 코드 200(OK) 반환
     */
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules() {
        // 상태 코드 200(OK)과 함께 조회된 일정 목록을 Body에 담아 반환
        return ResponseEntity.ok(calendarService.getSchedules());
    }

    /**
     * 특정 일정 조회 API
     * @param id : 조회할 일정의 id값
     * @return 특정 일정 정보와 상태 코드 200(OK) 반환
     */
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        // 상태 코드 200(OK)과 함께 조회된 일정을 Body에 담아 반환
        return ResponseEntity.ok(calendarService.getSchedule(id));
    }

    /**
     * 특정 일정 수정 API
     * @param id : 수정할 일정의 id값
     * @param dto : 클라이언트로부터 전달받은 수정할 일정 정보 (JSON타입)
     * @return 수정 완료된 일정 정보와 상태 코드 200(OK) 반환
     */
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedules(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        // 상태 코드 200(OK)과 함께 수정된 일정을 Body에 담아 반환
        return ResponseEntity.ok(calendarService.updateSchedule(id, dto));
    }


}
