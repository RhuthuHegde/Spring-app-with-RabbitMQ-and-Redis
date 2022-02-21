package com.example.demo.usermodel;

import com.example.demo.bookmodel.Books;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Users")
@Component
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "user_generator")
    @SequenceGenerator(allocationSize = 1, name = "user_generator",initialValue = 1,sequenceName = "user_sequence")
    private Long userId;
    private String firstName;
    private String lastName;
    private String emailId;

//    @JsonIgnore
//    @OneToMany(mappedBy = "users", orphanRemoval = true,targetEntity = Books.class)
//    private List<Books> books = new ArrayList<>();
//
//    public List<Books> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Books> books) {
//        this.books = books;
//    }


    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
