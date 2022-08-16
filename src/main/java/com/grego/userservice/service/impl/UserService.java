package com.grego.userservice.service.impl;

import com.grego.userservice.domain.User;
import com.grego.userservice.repository.IUserRepository;
import com.grego.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService<User> {

    private final IUserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public List<User> findAll() {
        return null;
    }
}

