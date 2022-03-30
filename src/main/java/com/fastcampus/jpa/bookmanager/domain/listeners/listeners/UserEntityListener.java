package com.fastcampus.jpa.bookmanager.domain.listeners.listeners;

import com.fastcampus.jpa.bookmanager.config.ApplicationContextProvider;
import com.fastcampus.jpa.bookmanager.domain.Entity.Accounts;
import com.fastcampus.jpa.bookmanager.domain.Entity.User;
import com.fastcampus.jpa.bookmanager.domain.Entity.UserHistory;
import com.fastcampus.jpa.bookmanager.repository.UserHistoryRepository;
import org.springframework.beans.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {
    @PostUpdate
    @PostPersist
    public void preUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = ApplicationContextProvider.getContext().getBean(UserHistoryRepository.class);
        User user = (User) o;

        UserHistory userHistory = UserHistory.builder()
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .user(user)
                .build();

        userHistoryRepository.save(userHistory);
    }

}
