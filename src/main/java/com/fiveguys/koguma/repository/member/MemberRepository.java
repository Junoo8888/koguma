package com.fiveguys.koguma.repository.member;

import com.fiveguys.koguma.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByNickname(String nickname);

    Optional<Member> findByNickname(String nickname);

    boolean existsById(Long Id);
    Optional<Member> findById(Long id);


}