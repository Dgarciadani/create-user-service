package com.grego.userservice.service;

import java.util.List;

public interface IPhoneService<T> {
    T create(T t);

    T update(T t);

    void delete(Long id);

    List<T> findAll();

}

