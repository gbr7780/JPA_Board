package com.JPA_Board.board.dto;

import com.JPA_Board.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    private String title;   // 제목
    private String content; // 내용
    private String writer;  // 작성자
    private char deleteYn;  // 삭제 여부

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .writer("111")
                .hits(0)
                .deleteYn(deleteYn)
                .build();
    }
}
