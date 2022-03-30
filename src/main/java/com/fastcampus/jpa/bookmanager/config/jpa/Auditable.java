package com.fastcampus.jpa.bookmanager.config.jpa;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setModifiedAt(LocalDateTime ModifiedAt);
}
