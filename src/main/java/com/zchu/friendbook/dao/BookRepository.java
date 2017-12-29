package com.zchu.friendbook.dao;

import com.zchu.friendbook.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>{
}
