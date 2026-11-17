package dev.portfolio.libraryflow.domain;
import jakarta.persistence.*;
@Entity @Table(name="books") public class Book {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=20) private String isbn;
 @Column(nullable=false,length=200) private String title;
 @Column(nullable=false,length=150) private String author;
 @Column(name="available_copies",nullable=false) private int availableCopies;
 @Column(name="total_copies",nullable=false) private int totalCopies;
 @Version private long version;
 protected Book(){}
 public Book(String isbn,String title,String author,int copies){
  if(isbn==null||isbn.isBlank()||title==null||title.isBlank()||author==null||author.isBlank()||copies<1)throw new IllegalArgumentException("Livro inválido");
  this.isbn=isbn.strip();this.title=title.strip();this.author=author.strip();this.availableCopies=copies;this.totalCopies=copies;
 }
 public void borrow(){if(availableCopies<=0)throw new IllegalStateException("Nenhum exemplar disponível");availableCopies--;}
 public void returnCopy(){if(availableCopies>=totalCopies)throw new IllegalStateException("Todos os exemplares já estão disponíveis");availableCopies++;}
 public Long getId(){return id;} public String getIsbn(){return isbn;} public String getTitle(){return title;} public String getAuthor(){return author;}
 public int getAvailableCopies(){return availableCopies;} public int getTotalCopies(){return totalCopies;}
}
