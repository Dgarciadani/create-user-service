package com.grego.userservice.service;

import java.util.List;

public interface IPhoneService<T> {
    void create(T t);

    void saveAll(List<T> t);


}

