package com.example.demo.services;
import com.example.demo.usermodel.Users;


import java.util.List;

public interface UserService {

    List<Users> getAllUsers();
    Users findByUserId(Long id);
    Users saveUsers(Users user);
    Long count();
    Users updateUsers(Long id,Users user);
    void deleteUsers(Long id);

}
