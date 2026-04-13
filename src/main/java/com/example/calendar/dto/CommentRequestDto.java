package com.example.calendar.dto;

import com.example.calendar.entity.Comment;
import lombok.Getter;
import lombok.Setter;

/**
 * 댓글 등록 요청 시 클라이언트로부터 전달받는 데이터 바구니(DTO)입니다.
 * 사용자가 입력한 정보를 담아 서비스 계층으로 전달하는 역할을 합니다.
 */
@Getter
@Setter
public class CommentRequestDto {
    private Long scheduleId;
    private String commentContent;
    private String writerName;
    private String password;

    /**
     * DTO에 담긴 사용자 입력 데이터를 바탕으로 Comment 엔티티 객체를 생성합니다.
     * 주로 서비스 계층에서 DB 저장(save)을 위해 호출됩니다.
     * * @return 빌더 패턴이 적용된 Comment 엔티티 객체
     */
    public Comment toEntity() {
        return Comment.builder()
                .scheduleId(this.scheduleId)
                .commentContent(this.commentContent)
                .writerName(this.writerName)
                .password(this.password)
                .build();
    }
}
