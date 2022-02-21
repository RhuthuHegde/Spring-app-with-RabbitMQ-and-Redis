package com.example.demo.services.implementation;

import com.example.demo.services.UserService;
import com.example.demo.userRepository.UserRepository;
import com.example.demo.usermodel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users findByUserId(Long id) {
        return userRepository.findUsersByUserId(id);
    }

    @Override
    public Users saveUsers(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public Users updateUsers(Long id, Users user) {
        Users updated_user=userRepository.findUsersByUserId(id);
//                .orElseThrow(
//                ()->new ResourceNotFoundException("Book id not found with id "+id));
        updated_user.setFirstName(user.getFirstName());
        updated_user.setLastName(user.getLastName());
        updated_user.setEmailId(user.getEmailId());
//        updated_user.setBooks(user.getBooks());
        userRepository.save(updated_user);
        return updated_user;
    }

    @Override
    public void deleteUsers(Long id) {
        Users deleteUser= userRepository.findUsersByUserId(id);
//                .orElseThrow(
//                ()->new ResourceNotFoundException("Book id not found with id "+id)
//        );
        userRepository.delete(deleteUser);
    }
}
