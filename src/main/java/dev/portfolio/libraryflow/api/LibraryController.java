package dev.portfolio.libraryflow.api;
import dev.portfolio.libraryflow.api.Requests.*;
import dev.portfolio.libraryflow.api.Responses.*;
import dev.portfolio.libraryflow.service.LibraryService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1") public class LibraryController {
 private final LibraryService service;
 public LibraryController(LibraryService service){this.service=service;}
 @PostMapping("/books") public ResponseEntity<BookView> book(@Valid @RequestBody BookCreate r){var b=service.createBook(r);return ResponseEntity.created(URI.create("/api/v1/books/"+b.id())).body(b);}
 @GetMapping("/books") public List<BookView> books(){return service.books();}
 @GetMapping("/books/{id}") public BookView book(@PathVariable Long id){return service.book(id);}
 @PostMapping("/members") public ResponseEntity<MemberView> member(@Valid @RequestBody MemberCreate r){var m=service.createMember(r);return ResponseEntity.created(URI.create("/api/v1/members/"+m.id())).body(m);}
 @GetMapping("/members") public List<MemberView> members(){return service.members();}
 @PostMapping("/loans") public ResponseEntity<LoanView> borrow(@Valid @RequestBody LoanCreate r){var l=service.borrow(r);return ResponseEntity.created(URI.create("/api/v1/loans/"+l.id())).body(l);}
 @GetMapping("/loans/{id}") public LoanView loan(@PathVariable Long id){return service.loan(id);}
 @GetMapping("/members/{id}/loans") public List<LoanView> memberLoans(@PathVariable Long id){return service.memberLoans(id);}
 @GetMapping("/loans/active") public List<LoanView> active(){return service.activeLoans();}
 @PatchMapping("/loans/{id}/return") public LoanView returnLoan(@PathVariable Long id){return service.returnLoan(id);}
}
