package com.grego.userservice.service.impl;

import com.grego.userservice.domain.Phone;
import com.grego.userservice.repository.IPhoneRepository;
import com.grego.userservice.service.IPhoneService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService implements IPhoneService<Phone> {
    private final static Logger LOGGER = LoggerFactory.getLogger(PhoneService.class);

    private final IPhoneRepository repository;

    @Override
    public Phone create(Phone phone) {
        return null;
    }

    @Override
    public List<Phone> saveAll(List<Phone> t) {
        LOGGER.info("Saving phones");
        return repository.saveAll(t);
    }


}

