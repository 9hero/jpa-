package com.fastcampus.jpa.bookmanager.domain.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BasicTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 맵바이시 해당 엔티티 포린키 사라짐
    @ToString.Exclude
    @OneToOne(optional = true) // set'EntityName'() 하면 id값 자동으로 설정해줌
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "bookReviewInfo")
    private Book book;

    private float average_review_score;

    private int review_count;
}
