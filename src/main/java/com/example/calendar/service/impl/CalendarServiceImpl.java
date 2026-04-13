package com.example.calendar.service.impl;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.repository.CalendarRepository;
import com.example.calendar.service.CalendarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CalendarService 인터페이스의 구현체입니다.
 * 실제 데이터베이스와의 상호작용 및 비즈니스 로직을 수행합니다.
 */
@Service
@RequiredArgsConstructor    // 역할은 컨트롤러와 동일
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    /**
     * 일정을 저장하고 저장된 결과를 반환합니다.
     * @param requestDto : 클라이언트로부터 전달받은 일정 정보
     * @return 저장 완료된 일정 정보(비밀번호 제외)
     */
    @Override
    @Transactional  // 데이터 저장 중 예외 발생시 롤백을 위해 작성
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {

        // 매개 변수로 받은 요청DTO를 Entity로 변환 후
        // Repository를 통해 DB에 저장(이때 JPA가 Insert 쿼리문을 자동 생성)
        Schedule saved = calendarRepository.save(requestDto.toEntity());

        // 위에서 저장한 Entity를 다시 응답DTO로 변환 후 반환
        return new ScheduleResponseDto(saved);
    }

    /**
     * 전체 일정을 조회합니다.
     * @return 전체 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    @Override
    @Transactional
    public List<ScheduleResponseDto> getSchedules() {
        return calendarRepository.findAllByOrderByIdDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    /**
     * 특정 일정을 조회합니다.
     * @param id : 검색할 일정의 id값
     * @return 요청한 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    @Override
    public ScheduleResponseDto getSchedule(Long id) {
        return calendarRepository.findById(id).map(ScheduleResponseDto::new).orElseThrow(() -> new IllegalArgumentException("조회 실패"));
    }

}
