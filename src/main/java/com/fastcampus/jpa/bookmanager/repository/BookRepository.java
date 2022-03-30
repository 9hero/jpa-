package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Entity.Book;
import com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategoli;
import com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query(value = "select b from Book b "
        + "where name =?1 and createdAt <= ?2 and updatedAt <= ?3 and category is null")
    List<Book> findByNameRecently(String name, LocalDateTime createdAt, LocalDateTime updatedAt);


    @Query(value = "select b from Book b "
        + "where name =:name and createdAt <= :createdAt and updatedAt <= :updatedAt and category is null")
    List<Book> findByNameRecentlyParam(
            @Param("name") String name,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "select b.name as name ,b.category as category from Book b ")
    List<Tuple> findBookNameAndCategory();

    // 인터페이스 활용시 쿼리문에 as로 인터페이스 get메소드와 이름값 맞춰줘야함
    @Query(value = "select b.name as name ,b.category as categoryies from Book b ")
    List<BookNameAndCategoli> findBookNameAndCategoryInterface();

    // dto 버전
    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name as name ,b.category as category) from Book b ")
    List<BookNameAndCategory> findDto();

    // dto + page
    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name as name ,b.category as category) from Book b ")
    Page<BookNameAndCategory> findDto(Pageable pageable);
    //@Query 사용시 Pageable 도 지원함

    // entity where절 무시됨
    @Query(value = "select * from book",nativeQuery = true)
    List<Book> findAllCustom();

    //native 쿼리 사용이유: 대용량 데이터 성능 이슈 where 로 id 하나 하나 찾아 넣어주면 성능저하하

    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
