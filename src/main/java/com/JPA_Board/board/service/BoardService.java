package com.JPA_Board.board.service;

import com.JPA_Board.board.dto.BoardRequestDto;
import com.JPA_Board.board.dto.BoardResponseDto;
import com.JPA_Board.board.entity.Board;
import com.JPA_Board.board.paging.CommonParams;
import com.JPA_Board.board.paging.Pagination;
import com.JPA_Board.board.repository.BoardMapper;
import com.JPA_Board.board.repository.BoardRepository;
import com.JPA_Board.exception.CustomException;
import com.JPA_Board.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

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
     * 게시글 리스트 조회 - (삭제 여부 기준)
     */
    public List<BoardResponseDto> findAllByDeleteYn(final char deleteYn){
        Sort sort = Sort.by(Sort.Direction.DESC,"id","createdDate");
        List<Board> list = boardRepository.findAllByDeleteYn(deleteYn,sort);
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

    /**
     * 게시글 상세정보 조회
     */
    @Transactional
    public BoardResponseDto findById(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.increaseHits();  // 조회수 증가
        return new BoardResponseDto(entity);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public Long delete(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }

    /**
     * mybatis 연결 테스트
     */
    public int count(int num) {
        return boardMapper.mybatisTest(num);
    }

    /**
     * 게시글 리스트 조회 - (With. paginaation information)
     */
    public Map<String, Object> findAll(CommonParams params) {

        // 게시글 수 조회
        int count = boardMapper.count(params);

        // 등록된 게시글이 없는 경우, 로직 종료
        if(count < 1) {
            return Collections.emptyMap();
        }

        // 페이지네이션 정보 계산
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 게시글 리스트 조회
        List<BoardResponseDto> list = boardMapper.findAll(params);

        // 데이터 변환
        HashMap<String, Object> response = new HashMap<>();
        response.put("params", params);
        response.put("list", list);
        return response;
    }
}
