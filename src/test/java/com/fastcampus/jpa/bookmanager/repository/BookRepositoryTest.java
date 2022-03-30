package com.fastcampus.jpa.bookmanager.repository;

//import com.fastcampus.jpa.bookmanager.config.jpa.JpaConfig;
import com.fastcampus.jpa.bookmanager.domain.Entity.Book;
import com.fastcampus.jpa.bookmanager.domain.Entity.Publisher;
import com.fastcampus.jpa.bookmanager.domain.Entity.Review;
import com.fastcampus.jpa.bookmanager.domain.Entity.User;
import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    void converterTest() {
//        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("또다른 전문서적");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);
        System.out.println(bookRepository.findRawRecord().values());
    }

    @Test
    void nativeQueryTest() {
        bookRepository.findAllCustom().forEach(System.out::println);

    }

    @Test
    void queryTest() {
//        bookRepository.findAll().forEach(System.out::println);

//        System.out.println("findByNameRecently : "+bookRepository.findByNameRecently("제피에이", LocalDateTime.now(),LocalDateTime.now()));
//        System.out.println("findByNameRecentlyParam : " +bookRepository.findByNameRecentlyParam("제피에이", LocalDateTime.now(),LocalDateTime.now()));
        /* class dto
        bookRepository.findBookNameAndCategory().forEach(tuple -> {
            System.out.println(tuple.get(0)+ " : "+ tuple.get(1));
        })
        ;
         */
        /*
        // interface dto
        bookRepository.findBookNameAndCategoryInterface().forEach(s -> {
            System.out.println(s.getName()+" : "+s.getCategoryies());
        });
.
         */
//        bookRepository.findDto().forEach(System.out::println);
        bookRepository.findDto(PageRequest.of(0,2)).forEach(System.out::println);
    }

    @Test
    void bookRemoveCascadeTest(){
        bookRepository.deleteById(1L);

        bookRepository.findAll().stream().map(Book::getPublisher).forEach(System.out::println);
    }

    @Test
    @Transactional
    void casCade(){
        Book book = new Book();
        book.setName("JPA 패키지");

//        bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("패캠");

//        publisherRepository.save(publisher);

        book.setPublisher(publisher);
        bookRepository.save(book);


        System.out.println("Books : " + bookRepository.findAll().get(3));
        System.out.println("Pubs : "+ publisherRepository.findAll());

        Book book1 = bookRepository.findById(4L).get();
        book1.getPublisher().setName("패스트 캠퍼스");

        bookRepository.save(book1);

        System.out.println("publisher: " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(4L).get();
//        bookRepository.delete(book2);

        book2.setPublisher(null);
        bookRepository.save(book2);
        System.out.println("Books : ");
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("Pubs : "+ publisherRepository.findAll());
        System.out.println("book2-pub : " + bookRepository.findById(4L).get().getPublisher());


    }


    @Test
    @Transactional
    void bookRelation(){
//        givenBookAndReview();

        User user = userRepository.findById(7L).orElseThrow(RuntimeException::new);

        System.out.println("REVIEW : "+user.getReviews());
        System.out.println("BOOK : "+ user.getReviews().get(0).getBook());
        System.out.println("PUblisher : "+user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(),givenBook(givenPublisher()));
    }

    private Book givenBook(Publisher publisher) {
        Book book = Book.builder()
                .name("제피에이")
                .category("program")
                .publisher(publisher)
                .build();
        return bookRepository.findById(5L).orElseThrow(RuntimeException::new);
    }

    private Publisher givenPublisher(){
        Publisher publisher =publisherRepository.findAll().get(0);
        System.out.println(publisher.getId() + "<<<< pid");
        return publisher;
    }

    private User givenUser() {
        return userRepository.findById(7L).orElseThrow(RuntimeException::new);
    }

    private void givenReview(User user, Book book) {
        Review review = Review.builder()
                .title("인생을바꺼주게")
                .content("인생이바뀌었습니다")
                .score(5.0f)
                .user(user)
                .book(book)
                .build();

    }

    @Test
    @Rollback(value = false)
    void bookTest(){
        bookRepository.save(Book.builder()
                .name("초격차")
//                .author_id(1L)
//                .publisher_id(1L)
                        .category("programing")
                .build());

        System.out.println(bookRepository.findAll());

    }

    @Test
    @Rollback
    void updateCheck(){
        Optional<Book> book = bookRepository.findById(2L);
        book.ifPresent(book1 -> {
            book1.setName("아아 변경되었다");
            bookRepository.save(book1);
        });
    }

}