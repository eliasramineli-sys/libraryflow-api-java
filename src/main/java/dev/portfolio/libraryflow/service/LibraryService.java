package dev.portfolio.libraryflow.service;
import dev.portfolio.libraryflow.api.Requests.*;
import dev.portfolio.libraryflow.api.Responses.*;
import dev.portfolio.libraryflow.domain.*;
import dev.portfolio.libraryflow.repository.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service public class LibraryService {
 private final BookRepository books;private final MemberRepository members;private final LoanRepository loans;
 public LibraryService(BookRepository books,MemberRepository members,LoanRepository loans){this.books=books;this.members=members;this.loans=loans;}
 @Transactional public BookView createBook(BookCreate r){if(books.existsByIsbn(r.isbn().strip()))throw new IllegalStateException("ISBN já cadastrado");return BookView.from(books.saveAndFlush(new Book(r.isbn(),r.title(),r.author(),r.copies())));}
 @Transactional(readOnly=true) public List<BookView> books(){return books.findAll().stream().map(BookView::from).toList();}
 @Transactional(readOnly=true) public BookView book(Long id){return BookView.from(books.findById(id).orElseThrow(()->new NoSuchElementException("Livro não encontrado")));}
 @Transactional public MemberView createMember(MemberCreate r){String email=r.email().strip().toLowerCase(Locale.ROOT);if(members.existsByEmail(email))throw new IllegalStateException("E-mail já cadastrado");return MemberView.from(members.saveAndFlush(new Member(r.name(),email)));}
 @Transactional(readOnly=true) public List<MemberView> members(){return members.findAll().stream().map(MemberView::from).toList();}
 @Transactional public LoanView borrow(LoanCreate r){
  Book book=books.findById(r.bookId()).orElseThrow(()->new NoSuchElementException("Livro não encontrado"));
  Member member=members.findById(r.memberId()).orElseThrow(()->new NoSuchElementException("Leitor não encontrado"));
  book.borrow();books.saveAndFlush(book);
  Instant now=Instant.now();return LoanView.from(loans.saveAndFlush(new Loan(book,member,now,now.plus(r.days(),ChronoUnit.DAYS))));
 }
 @Transactional public LoanView returnLoan(Long id){
  Loan loan=loans.findById(id).orElseThrow(()->new NoSuchElementException("Empréstimo não encontrado"));
  loan.returnAt(Instant.now());loan.getBook().returnCopy();books.saveAndFlush(loan.getBook());return LoanView.from(loans.saveAndFlush(loan));
 }
 @Transactional(readOnly=true) public LoanView loan(Long id){return LoanView.from(loans.findById(id).orElseThrow(()->new NoSuchElementException("Empréstimo não encontrado")));}
 @Transactional(readOnly=true) public List<LoanView> memberLoans(Long id){if(!members.existsById(id))throw new NoSuchElementException("Leitor não encontrado");return loans.findByMemberIdOrderByBorrowedAtDesc(id).stream().map(LoanView::from).toList();}
 @Transactional(readOnly=true) public List<LoanView> activeLoans(){return loans.findByReturnedAtIsNullOrderByDueAtAsc().stream().map(LoanView::from).toList();}
}
