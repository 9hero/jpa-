package com.fastcampus.jpa.bookmanager.domain.Entity;

import javax.persistence.*;

import com.fastcampus.jpa.bookmanager.domain.converter.BookStatusConverter;
import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@Where(clause = "deleted = false") where 절로 (컬럼 = 값) 인것을 다 붙임
public class Book extends BasicTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    //cascade.persist 북엔티티 persist 될때 같이 persist
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @ToString.Exclude
    private Publisher publisher;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    private boolean deleted;

//    @Convert(converter = BookStatusConverter.class) @Converter(autoApply = true) 이면 필요없음
    private BookStatus status; // 판매상태


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
    joinColumns = @JoinColumn(name ="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<Author>();

    public void addAuthor(Author author){
        author.addBooks(this);
        authors.add(author);
    }
}
