package dev.portfolio.libraryflow.repository;
import dev.portfolio.libraryflow.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member,Long>{boolean existsByEmail(String email);}
