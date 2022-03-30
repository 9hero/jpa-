package com.fastcampus.jpa.bookmanager.domain.Entity;

import com.fastcampus.jpa.bookmanager.domain.listeners.listeners.UserEntityListener;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "\"USER\"")
@EntityListeners(UserEntityListener.class)
public class User extends BasicTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();


    public enum Gender {

        MALE,
        FEMALE
    }
}
