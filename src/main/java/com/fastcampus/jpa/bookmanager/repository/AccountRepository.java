package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Entity.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long> {
    Accounts findByEmail(String email);
    Accounts findByUsername(String name);
    // After 키워드는 where 컬럼명 > 값임 =없는거 주의 날짜 구분할때 쓰삼 컬럼명 뒤에 쓰삼
    // 일반적으로 비교시 GreaterThan LessThan + Equals
    // First는 by이전에 붙이고 OrderBy column Direction
    Accounts findFirstByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime localDateTime);
    List<Accounts> findByCreatedAtBetween(LocalDateTime afterTime, LocalDateTime beforeTime);
    List<Accounts> findByUserIdIsNotNull();
    List<Accounts> findByUsernameLike(String text);
    List<Accounts> findByUsernameStartingWith(String text);
    List<Accounts> findByUsernameEndingWith(String text);
    Accounts findFirstByEmailEndingWith(String email, Sort orders);
    Page<Accounts> findByCreatedAtAfter(LocalDateTime created, Pageable pageable);

}
