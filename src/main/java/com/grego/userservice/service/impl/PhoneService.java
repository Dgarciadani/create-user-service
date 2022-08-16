package com.grego.userservice.service.impl;

import com.grego.userservice.domain.Phone;
import com.grego.userservice.service.IPhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService implements IPhoneService<Phone> {

    @Override
    public Phone create(Phone phone) {
        return null;
    }

    @Override
    public Phone update(Phone phone) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Phone> findAll() {
        return null;
    }
}

