package com.fastcampus.jpa.bookmanager.domain.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "testdb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long aid;

    @Column(name = "column1")
    private String data;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private User user;
}
