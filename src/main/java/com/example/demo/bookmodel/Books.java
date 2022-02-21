package com.example.demo.bookmodel;

import com.example.demo.usermodel.Users;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Books")
@Component
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "book_generator")
    @SequenceGenerator(name="book_generator",allocationSize = 1,initialValue = 1,sequenceName = "book_sequence")
    private Long bookId;
    private String bookName;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_user_id",referencedColumnName = "userId")
//    private Users users;

//    public Users getUsers() {
//        return users;
//    }
//
//    public void setUsers(Users users) {
//        this.users = users;
//    }


    public Long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
