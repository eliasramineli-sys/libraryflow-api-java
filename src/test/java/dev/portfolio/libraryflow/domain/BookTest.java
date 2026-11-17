package dev.portfolio.libraryflow.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BookTest {
 @Test void borrowingDecreasesAvailableCopies(){Book b=new Book("9781234567890","Java","Autor",2);b.borrow();assertEquals(1,b.getAvailableCopies());}
 @Test void cannotBorrowWithoutCopies(){Book b=new Book("9781234567890","Java","Autor",1);b.borrow();assertThrows(IllegalStateException.class,b::borrow);}
 @Test void returnRestoresAvailability(){Book b=new Book("9781234567890","Java","Autor",1);b.borrow();b.returnCopy();assertEquals(1,b.getAvailableCopies());}
 @Test void cannotReturnMoreThanTotal(){Book b=new Book("9781234567890","Java","Autor",1);assertThrows(IllegalStateException.class,b::returnCopy);}
}
