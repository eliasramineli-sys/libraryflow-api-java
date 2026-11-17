package dev.portfolio.libraryflow.api;
import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<ProblemDetail> missing(NoSuchElementException e){return result(HttpStatus.NOT_FOUND,e.getMessage());}
 @ExceptionHandler({IllegalArgumentException.class,MethodArgumentNotValidException.class}) ResponseEntity<ProblemDetail> invalid(Exception e){return result(HttpStatus.BAD_REQUEST,"Dados inválidos");}
 @ExceptionHandler({IllegalStateException.class,DataIntegrityViolationException.class,ObjectOptimisticLockingFailureException.class}) ResponseEntity<ProblemDetail> conflict(Exception e){return result(HttpStatus.CONFLICT,"Operação conflitante ou indisponível");}
 private ResponseEntity<ProblemDetail> result(HttpStatus s,String detail){return ResponseEntity.status(s).body(ProblemDetail.forStatusAndDetail(s,detail));}
}
