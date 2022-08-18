package com.grego.userservice.service;

import com.grego.userservice.domain.dto.UserSendDto;

import java.time.LocalDate;
import java.util.UUID;

public interface IUserService<T> {
    UserSendDto create(T t);

    UserSendDto disableUser(String email);

    UserSendDto findById(UUID id);

    UserSendDto findUserByEmail(String email);

    boolean existsByEmail(String email);

    void saveToken(String email, String token);

}


