package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Entity.Author;
import com.fastcampus.jpa.bookmanager.domain.Entity.Book;
import com.fastcampus.jpa.bookmanager.domain.Entity.Publisher;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    @Transactional
    void create(){
        Author author = authorRepository.findAll().get(0);
        System.out.println("작가 이름 "+ author.getName());

        Book book = new Book();
        book.setName("newbook");
        System.out.println(">>>>>>>>middle of set");
        book.setCategory("horror");
        System.out.println(">>>> up set horror down addauthor");
        book.addAuthor(author);
        System.out.println(">>>> up author down setPuble");
        book.setPublisher(publisherRepository.findById(1L).get());
        System.out.println("-----before save");
        bookRepository.save(book);
//        bookRepository.flush();
        System.out.println(">>> 에드북 2 ");
        Author dbauthor = authorRepository.findByName("라캉");
        System.out.println("------up getAuthor donw getBooks");
        dbauthor.getBooks().forEach(System.out::println);

        /*
        과정 현재 연관관계 주인은 책임
        1. 작가, 책 생성
        2. 작가엔티티 책엔티티 연결
        3. save 마지막
         */

    }


    @Test
    @Transactional
    void selectBook(){
        Author author = authorRepository.getById(1L);
        System.out.println(author.getName());
        System.out.println("book name : "+author.getBooks().iterator().next().getName());
    }

    @Test
    @Transactional
    void manyToManyTest(){
        Book book1 = givenBook("책1");
        Book book2 = givenBook("책2");
        Book book3 = givenBook("개발책1");
        Book book4 = givenBook("개발책2");

        Author author1 = Author.builder().name("f").country("g").build();
        Author author2 = null;



        bookRepository.saveAll(Lists.newArrayList(book1,book2,book3,book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        System.out.println("authors through book : "+bookRepository.findAll().get(2).getAuthors());
        System.out.println("books through author : "+authorRepository.findAll().get(0).getName());
    }

    private Book givenBook(String name){
        return bookRepository.save(Book.builder().name(name).build());
    }
    private Author givenAuthor(String name){
        return authorRepository.save(Author.builder().name(name).build());
    }
}