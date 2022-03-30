package com.fastcampus.jpa.bookmanager.domain.listeners.listeners;

import com.fastcampus.jpa.bookmanager.config.jpa.Auditable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {

    @PrePersist
    public void prePersist(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());

        }
    }

    @PreUpdate
    public void PreUpdate(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setModifiedAt(LocalDateTime.now());
        }
    }
}
