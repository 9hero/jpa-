package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {

    List<UserHistory> findByUserId(Long userId);
}
