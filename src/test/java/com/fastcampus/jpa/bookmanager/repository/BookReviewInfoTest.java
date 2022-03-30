package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Entity.Book;
import com.fastcampus.jpa.bookmanager.domain.Entity.BookReviewInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;


    private Book book(){
        return bookRepository.findById(1L).orElseThrow(RuntimeException::new);
    }

    @Test
    void cTest(){
        //given
        Book book1 = book();
        System.out.println(book1.getBookReviewInfo());
        /*
        BookReviewInfo bookReviewInfo = BookReviewInfo.builder()
                .average_review_score(4.5F)
                .review_count(2)
                .build();
        bookReviewInfo.setBook(book1);


        //when

        bookReviewInfoRepository.save(bookReviewInfo);

        //then
        Assertions.assertEquals(
                bookReviewInfoRepository.findAll().get(0),
                bookReviewInfo
        );

         */
    }
/*
    @Test
    void join(){
        bookRepository.findById(1L).ifPresent(book -> {
            System.out.println(bookReviewInfoRepository.findByBookId(book.getId())
                    .toString());
        });
    }

 */


}