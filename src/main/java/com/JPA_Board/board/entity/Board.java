package com.JPA_Board.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // PK

    private String title;   // 제목

    private String content; // 내용

    private String writer;  // 작성자

    private int hits;   // 조회수

    private char deleteYn;  // 삭제 여부

    private LocalDateTime createdDate = LocalDateTime.now();    // 생성일

    private LocalDateTime modifiedDate; // 수정일


    @Builder
    public Board(String title, String content, String writer, int hits, char deleteYn, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.deleteYn = deleteYn;
    }

    /**
     * 게시글 수정
     * @param title
     * @param content
     * @param writer
     */
    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this. writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }

    /**
     * 조회수 증가
     */
    public void increaseHits() {
        this.hits++;
    }

    /**
     * 게시글 삭제
     */
    public void delete() {
        this.deleteYn = 'Y';
    }
}
