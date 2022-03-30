package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    void transactionTest(){


        try {
            bookService.putBookAndAuthor();
        }catch (RuntimeException error){ //런타임 예외는 rollback 해줌
            //하지만 checked exception은 그냥 커밋해버림 -> Exception 인 경우
            System.out.println(">>>"+error.getMessage());
        }
        System.out.println("books: "+bookRepository.findAll());
        System.out.println("authors: "+ authorRepository.findAll());
    }



}