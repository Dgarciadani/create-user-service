package com.grego.userservice.service.impl;

import com.grego.userservice.domain.Phone;
import com.grego.userservice.domain.dto.PhoneReceivedDto;
import com.grego.userservice.repository.IPhoneRepository;
import com.grego.userservice.service.IPhoneService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PhoneService implements IPhoneService<PhoneReceivedDto> {
    private final static Logger LOGGER = LoggerFactory.getLogger(PhoneService.class);

    private final IPhoneRepository repository;

    private ModelMapper modelmapper;


    @Override
    public void create(PhoneReceivedDto phoneReceivedDto) {
        try {
            Phone phone = modelmapper.map(phoneReceivedDto, Phone.class);
            repository.save(phone);
        }
        catch (Exception e) {
            LOGGER.error("Error creating phone: {}", e.getMessage());
        }

    }

    @Override
    public void saveAll(List<PhoneReceivedDto> phones) {
        try {
            List<Phone> phonesEntities = phones.stream().map(phone -> modelmapper.map(phone, Phone.class)).toList();
            repository.saveAll(phonesEntities);
        }
        catch (ValidationException e) {
            LOGGER.error("Error saving phones: {}", e.getMessage());
        }

    }
    //map to entity
    private Phone mapToEntity(PhoneReceivedDto phoneReceivedDto) {
        return modelmapper.map(phoneReceivedDto, Phone.class);
    }

}

