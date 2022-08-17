package com.grego.userservice.service;

import java.util.List;

public interface IPhoneService<T> {
    T create(T t);

    List<T> saveAll(List<T> t);


}

