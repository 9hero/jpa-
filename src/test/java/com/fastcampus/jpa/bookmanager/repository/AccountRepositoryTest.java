package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Entity.Accounts;
import com.fastcampus.jpa.bookmanager.domain.Entity.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository userRepository;


    @Test
    void del(){
        userRepository.deleteById(22L);
    }

    @Test
    @Rollback
    @Transactional
    void create(){
        userRepository.save(
                Accounts.builder()
                        .userRole(Accounts.Role.ROLE_USER)
                        .password("fdsa")
                        .email("pwflp@alfa.com")
                        .username("jhiukxo")
                        .build()
        );
    }

    @Test
    void crud(){
        System.out.println(userRepository.findFirstByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime.now().minusDays(5))
        );
//        userRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt")).forEach(System.out::println);
    }

    @Test
    @Transactional
    void read(){

        userRepository.findAllById(Lists.newArrayList(1L,2L,3L,4L,5L))
                .forEach(System.out::println);
    }

/*    @Test
    void exampleMatcher(){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("createdAt",contains())
                ;

        Example<User> example = Example.of(User.builder()
                .createdAt(LocalDateTime.parse("2022-03-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"))).username("coco").build());

        userRepository.findAll(example).forEach(System.out::println);
    }

 */


    @Test
    @Transactional
    void page(){
        Sort sort = Sort.by(Sort.Direction.DESC,"createdAt");
        Page<Accounts> userPage = userRepository.findAll
                (PageRequest.of(1,3,sort));

        System.out.println("page:  " + userPage);
        System.out.println("getNumberOfElements:  " + userPage.getNumberOfElements());
        System.out.println("getSort:  " + userPage.getSort());
        System.out.println("getSize:  " + userPage.getSize());
        System.out.println("page getTotalElements:  " + userPage.getTotalElements());
        System.out.println("total page:  " + userPage.getTotalPages());

        userPage.getContent().forEach(System.out::println);
    }

    @Test
    void select(){
//        System.out.println("파바이멜"+userRepository.findByEmail("koreaman@mavm.com"));
//        System.out.println("파바네임"+userRepository.findByUsername("고그모우"));
//        System.out.println(userRepository.findFirst1ByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime.now().minusDays(3L)));
//        System.out.println(userRepository.findByCreatedAtBetween(LocalDateTime.now().plusDays(3L),LocalDateTime.now().minusDays(3L)));
//        System.out.println(userRepository.findByUserIdIsNotNull());
//        System.out.println(userRepository.findByUsernameLike("%고%"));
//        System.out.println(userRepository.findByUsernameStartingWith("고"));
//        System.out.println(userRepository.findByUsernameEndingWith("고"));
//        System.out.println(userRepository.findFirstByEmailEndingWith(".com",Sort.by(Sort.Order.desc("createdAt"))));
        System.out.println(userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(4L),PageRequest.of(0,3,Sort.by(Sort.Order.desc("userId"))))
                );
    }


    @Test
    void listenerTest(){
        Accounts user = Accounts.builder()
                .email("asdasfafasd@asddsa.com")
                .username("loagemd")
                .password("pawrd")
                .userRole(Accounts.Role.ROLE_USER)
                .build();
        userRepository.save(user);

        Accounts user1 = userRepository.findById(3L).orElseThrow(RuntimeException::new);
        user1.setUserRole(Accounts.Role.ROLE_ADMIN);
        user1.setUsername("유저이름업데이트");
        System.out.println(">>>업데이트 체크");
        userRepository.save(user1);

        userRepository.deleteById(19L);
    }
}