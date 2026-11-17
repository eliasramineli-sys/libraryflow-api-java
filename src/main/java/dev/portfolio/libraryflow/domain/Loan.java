package dev.portfolio.libraryflow.domain;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="loans") public class Loan {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="book_id") private Book book;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="member_id") private Member member;
 @Column(name="borrowed_at",nullable=false) private Instant borrowedAt;
 @Column(name="due_at",nullable=false) private Instant dueAt;
 @Column(name="returned_at") private Instant returnedAt;
 protected Loan(){}
 public Loan(Book book,Member member,Instant borrowedAt,Instant dueAt){
  if(book==null||member==null||borrowedAt==null||dueAt==null||!dueAt.isAfter(borrowedAt))throw new IllegalArgumentException("Empréstimo inválido");
  this.book=book;this.member=member;this.borrowedAt=borrowedAt;this.dueAt=dueAt;
 }
 public void returnAt(Instant time){if(returnedAt!=null)throw new IllegalStateException("Empréstimo já devolvido");if(time==null||time.isBefore(borrowedAt))throw new IllegalArgumentException("Data de devolução inválida");returnedAt=time;}
 public boolean isOverdue(Instant now){return returnedAt==null&&dueAt.isBefore(now);}
 public Long getId(){return id;} public Book getBook(){return book;} public Member getMember(){return member;}
 public Instant getBorrowedAt(){return borrowedAt;} public Instant getDueAt(){return dueAt;} public Instant getReturnedAt(){return returnedAt;}
}
