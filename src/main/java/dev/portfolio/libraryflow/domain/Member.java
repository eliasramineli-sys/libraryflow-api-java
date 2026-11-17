package dev.portfolio.libraryflow.domain;
import jakarta.persistence.*;
@Entity @Table(name="members") public class Member {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,unique=true,length=254) private String email;
 protected Member(){}
 public Member(String name,String email){if(name==null||name.isBlank()||email==null||email.isBlank())throw new IllegalArgumentException("Leitor inválido");this.name=name.strip();this.email=email.strip().toLowerCase(java.util.Locale.ROOT);}
 public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
}
