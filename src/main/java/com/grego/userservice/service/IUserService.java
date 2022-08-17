package com.grego.userservice.service;

import com.grego.userservice.domain.dto.UserSendDto;

import java.util.List;
import java.util.UUID;

public interface IUserService<T> {
    UserSendDto create(T t);

    UserSendDto update(T t);

    UserSendDto findById(UUID id);

    boolean existsByEmail(String email);

}


