package dev.portfolio.libraryflow.api;
import dev.portfolio.libraryflow.domain.*;
import java.time.Instant;
public final class Responses {
 private Responses(){}
 public record BookView(Long id,String isbn,String title,String author,int totalCopies,int availableCopies){public static BookView from(Book b){return new BookView(b.getId(),b.getIsbn(),b.getTitle(),b.getAuthor(),b.getTotalCopies(),b.getAvailableCopies());}}
 public record MemberView(Long id,String name,String email){public static MemberView from(Member m){return new MemberView(m.getId(),m.getName(),m.getEmail());}}
 public record LoanView(Long id,Long bookId,Long memberId,Instant borrowedAt,Instant dueAt,Instant returnedAt,boolean overdue){
  public static LoanView from(Loan l){return new LoanView(l.getId(),l.getBook().getId(),l.getMember().getId(),l.getBorrowedAt(),l.getDueAt(),l.getReturnedAt(),l.isOverdue(Instant.now()));}
 }
}
