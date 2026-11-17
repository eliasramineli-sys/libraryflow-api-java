package dev.portfolio.libraryflow.domain;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class LoanTest {
 @Test void returnCanHappenOnce(){Instant now=Instant.now();Loan l=new Loan(new Book("123","Java","Autor",1),new Member("Ana","ana@example.com"),now,now.plusSeconds(86400));l.returnAt(now.plusSeconds(10));assertNotNull(l.getReturnedAt());assertThrows(IllegalStateException.class,()->l.returnAt(now.plusSeconds(20)));}
 @Test void overdueWhenActivePastDueDate(){Instant now=Instant.now();Loan l=new Loan(new Book("123","Java","Autor",1),new Member("Ana","ana@example.com"),now.minusSeconds(86400*3),now.minusSeconds(86400));assertTrue(l.isOverdue(now));}
 @Test void returnedLoanNotOverdue(){Instant now=Instant.now();Loan l=new Loan(new Book("123","Java","Autor",1),new Member("Ana","ana@example.com"),now.minusSeconds(86400*3),now.minusSeconds(86400));l.returnAt(now);assertFalse(l.isOverdue(now));}
}
