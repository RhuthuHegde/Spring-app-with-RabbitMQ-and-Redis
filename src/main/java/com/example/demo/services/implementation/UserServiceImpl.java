package com.example.demo.services.implementation;

import com.example.demo.services.UserService;
import com.example.demo.userRepository.UserRepository;
import com.example.demo.usermodel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Users")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(value = "Users")
    public List<Users> getAllUsers() {
        System.out.println("Getting from the DB");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(key="#id")
    public Users findByUserId(Long id) {
        System.out.println("Getting from the DB");
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
    @CachePut(key="#id")
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
    @CacheEvict(key="#id",allEntries = true)
    public void deleteUsers(Long id) {
        Users deleteUser= userRepository.findUsersByUserId(id);
//                .orElseThrow(
//                ()->new ResourceNotFoundException("Book id not found with id "+id)
//        );
        userRepository.delete(deleteUser);
    }
}
