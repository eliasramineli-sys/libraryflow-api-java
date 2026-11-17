package dev.portfolio.libraryflow.api;
import jakarta.validation.constraints.*;
public final class Requests {
 private Requests(){}
 public record BookCreate(@NotBlank @Size(max=20) String isbn,@NotBlank @Size(max=200) String title,@NotBlank @Size(max=150) String author,@Min(1) int copies){}
 public record MemberCreate(@NotBlank @Size(max=120) String name,@NotBlank @Email @Size(max=254) String email){}
 public record LoanCreate(@NotNull @Positive Long bookId,@NotNull @Positive Long memberId,@Min(1) @Max(60) int days){}
}
