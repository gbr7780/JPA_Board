package com.JPA_Board.board.service;

import com.JPA_Board.board.dto.BoardRequestDto;
import com.JPA_Board.board.dto.BoardResponseDto;
import com.JPA_Board.board.entity.Board;
import com.JPA_Board.board.repository.BoardRepository;
import com.JPA_Board.exception.CustomException;
import com.JPA_Board.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    /*
    * 게시글 생성
    */
    @Transactional
    public Long save(final BoardRequestDto params){
        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    /*
     * 게시글 리스트 조회
     */
    public List<BoardResponseDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id","createdDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    /*
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final BoardRequestDto params){
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }

}