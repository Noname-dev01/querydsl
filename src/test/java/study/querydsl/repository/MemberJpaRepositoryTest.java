package study.querydsl.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest(){
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> findAll = memberJpaRepository.findAll();
        assertThat(findAll).containsExactly(member);

        List<Member> findByUsername = memberJpaRepository.findByUsername("member1");
        assertThat(findByUsername).containsExactly(member);
    }

    @Test
    public void basicQuerydslTest(){
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> findAll = memberJpaRepository.findAll_Querydsl();
        assertThat(findAll).containsExactly(member);

        List<Member> findByUsername = memberJpaRepository.findByUsername_Querydsl("member1");
        assertThat(findByUsername).containsExactly(member);
    }
}