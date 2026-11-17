package dev.portfolio.libraryflow.repository;
import dev.portfolio.libraryflow.domain.Loan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LoanRepository extends JpaRepository<Loan,Long>{
 List<Loan> findByMemberIdOrderByBorrowedAtDesc(Long memberId);
 List<Loan> findByReturnedAtIsNullOrderByDueAtAsc();
}
