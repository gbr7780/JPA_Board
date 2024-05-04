package com.JPA_Board.board.repository;

import com.JPA_Board.board.dto.BoardResponseDto;
import com.JPA_Board.board.paging.CommonParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface BoardMapper {

    /**
     * mybatis 테스트
     */
    int mybatisTest(final int params);

    /**
     * 게시글 수 조회
     */
    int count(final CommonParams params);

    /**
     * 게시글 리스트 조회
     */
    List<BoardResponseDto> findAll(final CommonParams params);


}

