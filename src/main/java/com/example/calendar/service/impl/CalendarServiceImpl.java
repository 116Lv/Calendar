package com.example.calendar.service.impl;

import com.example.calendar.dto.CommentRequestDto;
import com.example.calendar.dto.CommentResponseDto;
import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Comment;
import com.example.calendar.entity.Schedule;
import com.example.calendar.repository.CalendarRepository;
import com.example.calendar.repository.CommentRepository;
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

    private final CommentRepository commentRepository;

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
    public List<ScheduleResponseDto> getSchedules(String writerName) {
        if(writerName == null || writerName.isEmpty()) {
            return calendarRepository.findAllByOrderByEditDateDesc().stream().map(ScheduleResponseDto::new).toList();
        } else {
            return calendarRepository.findAllByOrderByEditDateDesc().stream().filter(s -> s.getWriterName().equals(writerName)).map(ScheduleResponseDto::new).toList();
        }
    }

    /**
     * 특정 일정을 조회합니다.
     * @param id : 검색할 일정의 id값
     * @return 요청한 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    @Override
    @Transactional
    public ScheduleResponseDto getSchedule(Long id) {
        return calendarRepository.findById(id).map(ScheduleResponseDto::new).orElseThrow(() -> new IllegalArgumentException("조회 실패"));
    }

    /**
     * 특정 일정을 수정합니다.
     * @param id : 수정할 일정의 id값
     * @param dto : 수정할 일정 정보가 담긴 요청 DTO
     * @return 수정된 일정 정보를 담은 응답 DTO (비밀번호 제외)
     */
    @Override
    @Transactional  // 데이터 변경을 감지해 자동으로 DB에 반영되게 하기 위해
    public ScheduleResponseDto updateSchedule(Long id, String password, ScheduleRequestDto dto) {

        // 수정할 일정 불러오기
        Schedule updatedSchedule = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("수정 실패"));

        // 비밀번호 비교
        if(updatedSchedule.getPassword().equals(password)) {
            // 해당일정의 변경점 반영
            updatedSchedule.update(dto.getTitle(), dto.getWriterName());
        } else {
            // 만일 다르다면 예외 처리
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 결과 반환
        return new ScheduleResponseDto(updatedSchedule);

        // 반환과 동시에 UPDATE 쿼리문 자동 실행 돼서 해당 변경점이 DB(메모리)의 내용도 변경
    }

    /**
     * 특정 일정을 삭제합니다.
     * @param id : 삭제할 일정의 id값
     */
    @Override
    @Transactional
    public void deleteSchedule(Long id, String password) {
        // 일단 먼저 해당 일정이 있는지 조회 후 삭제 진행
        Schedule deletedSchedule = calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을수 없습니다."));

        if(deletedSchedule.getPassword().equals(password)) {
            calendarRepository.delete(deletedSchedule);
        } else {
            // 만일 다르다면 예외 처리
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }

    /**
     * 특정 일정에 댓글을 작성합니다.
     * @param id : 작성할 일정의 id값
     * @param dto : 특정 일정에 작성할 댓글 정보가 담긴 요청 DTO
     * @return 작성된 댓글 정보를 담은 응답 DTO (비밀번호 제외)
     */
    @Override
    @Transactional
    public CommentResponseDto saveComment(Long id, CommentRequestDto dto) {
        // 일정이 있는지 먼저 확인
        calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글을 달 일정이 존재하지 않습니다."));

        // 해당 일정에 댓글이 10개 이상인지 확인 후 댓글 추가
        if(commentRepository.countByScheduleId(id) >= 10) {
            throw new IllegalArgumentException("한 일정에는 10개의 댓글만 작성 가능합니다.");
        }

        Comment saved = commentRepository.save(dto.toEntity());

        return new CommentResponseDto(saved);
    }

}
