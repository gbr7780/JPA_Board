package com.JPA_Board.board.controller;

import com.JPA_Board.board.dto.BoardRequestDto;
import com.JPA_Board.board.dto.BoardResponseDto;
import com.JPA_Board.board.service.BoardService;
import com.JPA_Board.exception.CustomException;
import com.JPA_Board.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;
    @GetMapping("/test")
    public String test(){
        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    }

    /*
     * 게시글 생성
     */
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDto params){
        return boardService.save(params);
    }

    /*
     *  게시글 리스트 조회
     */
    @GetMapping("/boards")
    public List<BoardResponseDto> findAll(@RequestParam final char deleteYn){
        return boardService.findAllByDeleteYn(deleteYn);
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
        return boardService.update(id, params);
    }

    /**
     * 게시글 상세정보 조회
     */
    @GetMapping("/boards/{id}")
    public BoardResponseDto findById(@PathVariable final Long id) {
        return boardService.findById(id);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/boards/{id}")
    public Long delete(@PathVariable final Long id) {
        return boardService.delete(id);
    }
}
