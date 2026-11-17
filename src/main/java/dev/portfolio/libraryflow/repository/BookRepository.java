package dev.portfolio.libraryflow.repository;
import dev.portfolio.libraryflow.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book,Long>{boolean existsByIsbn(String isbn);}
