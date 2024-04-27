package com.JPA_Board;

import com.JPA_Board.member.Gender;
import com.JPA_Board.member.Member;
import com.JPA_Board.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    // 회원 정보 생성
    @Test
    void saveMember(){
        Member saveParams = Member.builder()
                .loginId("gbr7780")
                .password("1234")
                .name("종하")
                .gender(Gender.M)
                .birthday(LocalDate.of(1997,8,9))
                .deleteYn(false)
                .build();

        Member member = memberRepository.save(saveParams);
        Assertions.assertEquals(member.getLoginId(),"gbr7780");
    }

    // 전체 회원 조회
    @Test
    void findAllMember(){
        memberRepository.findAll();
    }

    // 회원 상세정보 조회
    @Test
    void findMemberById(){
        Member member = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(member.getLoginId(),"gbr7780");
    }

    @Test
    void deleteMemberById(){
        memberRepository.deleteById(1L);
    }
}
