package com.example.calendar.controller;

import com.example.calendar.dto.CommentRequestDto;
import com.example.calendar.dto.CommentResponseDto;
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
     * @param writerName 작성자명별 필터링을 위한 선택적 파라미터 (null 허용)
     * @return 수정일 기준 내림차순으로 정렬된 일정 목록과 상태 코드 200(OK) 반환
     */
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(@RequestParam(required = false) String writerName) {
        // 상태 코드 200(OK)과 함께 조회된 일정 목록을 Body에 담아 반환
        return ResponseEntity.ok(calendarService.getSchedules(writerName));
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
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        // 상태 코드 200(OK)과 함께 수정된 일정을 Body에 담아 반환
        return ResponseEntity.ok(calendarService.updateSchedule(id, dto.getPassword(), dto));
    }

    /**
     * 특정 일정 삭제 API
     * @param id : 삭제할 일정의 id값
     * @return 상태 코드 204(No Content) 반환
     */
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        // 요청한 일정 삭제 처리
        calendarService.deleteSchedule(id, dto.getPassword());

        // 상태 코드 204(No Content)를 반환
        return ResponseEntity.noContent().build();
    }

    /**
     * 특정 일정 댓글 작성 API
     * @param id : 작성할 일정의 id값
     * @param dto : 특정 일정에 작성할 댓글 정보가 담긴 요청 DTO
     * @return 작성 완료된 댓글 정보와 상태 코드 201(Created) 반환
     */
    @PostMapping("/schedules/{id}/comments")
    public ResponseEntity<CommentResponseDto> saveComment(@PathVariable Long id, @RequestBody CommentRequestDto dto) {

        CommentResponseDto responseDto = calendarService.saveComment(id, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

}
