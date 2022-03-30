package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Entity.User;
import com.fastcampus.jpa.bookmanager.domain.Entity.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Test
    @Rollback(value = true)
    @Transactional
    void userRelationTest(){
        User user = User.builder()
                .name("sdfaasdf")
                .gender(User.Gender.MALE)
                .email("gogmow@nam.com")
                .build();
        userRepository.save(user);

        user.setName("updated");
        userRepository.save(user);

        user.setEmail("updated@fast.com");
        userRepository.save(user);

//        userHistoryRepository.findAll().forEach(System.out::println);

        List<UserHistory> result = userHistoryRepository.findByUserId(
                userRepository.findById(8L).get().getId()
        );

        result.forEach(System.out::println);

    }
}