package com.grego.userservice.service.impl;

import com.grego.userservice.domain.Phone;
import com.grego.userservice.domain.User;
import com.grego.userservice.domain.UserRoles;
import com.grego.userservice.domain.dto.UserReceivedDto;
import com.grego.userservice.domain.dto.UserSendDto;
import com.grego.userservice.exceptions.EmailAlreadyRegisteredException;
import com.grego.userservice.repository.IUserRepository;
import com.grego.userservice.security.jwt.JwtTokenUtil;
import com.grego.userservice.service.IPhoneService;
import com.grego.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService<UserReceivedDto>, UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;
    private final IPhoneService phoneService;
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtUtil;
    @Override
    public UserSendDto create(UserReceivedDto user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email already registered");
        } else {
            try {
                LOGGER.info("Creating user");
                LOGGER.info("password" + user.getPassword());
                User userEntity = modelMapper.map(user, User.class);
                LOGGER.info("Entidad creada");
                userEntity = this.initializer(userEntity);
                LOGGER.info("Entidad IGUALADA ");
                LOGGER.info(userEntity.toString());
                User savedUser = userRepository.save(userEntity);
                LOGGER.info("User saved");
                List<Phone> phones = user.getPhones().stream().peek(phone -> phone.setUser(savedUser)).toList();
                phoneService.saveAll(phones);
                LOGGER.info("User and phone created");
                UserSendDto map = modelMapper.map(savedUser, UserSendDto.class);
                String jwt = jwtUtil.generateToken(savedUser);
                map.setToken(jwt);
                return map;

            } catch (ConstraintViolationException e) {
                LOGGER.error(e.getMessage());
                throw e;
            }
        }
    }

    @Override
    public UserSendDto update(UserReceivedDto user) {
        //  return userRepository.save(user);
        return null;
    }

    @Override
    public UserSendDto findById(UUID id) {
        LOGGER.info("Finding user by id");
        LOGGER.debug("id: {}", id);
        User user = userRepository.findUserById(id);
        LOGGER.info("User found");
        LOGGER.debug("user: {}", user);
        return modelMapper.map(user, UserSendDto.class);
    }

    @Override
    public boolean existsByEmail(String email) {
        LOGGER.info("Checking if user exists by email");
        LOGGER.debug("email: {}", email);
        boolean exists = userRepository.existsByEmail(email);
        LOGGER.info("User exists: {}", exists);
        return exists;
    }


    // init user
    // encode password
    public User initializer(User user) {
        try {
            LOGGER.info("Initializing user");
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setCreated(LocalDate.now());
            user.setModified(LocalDate.now());
            user.setLastLogin(LocalDate.now());
            user.setUserRoles(UserRoles.USER);
            user.setActive(true);
           // user.setPhones(null);
            LOGGER.info("User initialized");
            return user;
        } catch (Exception e) {
            LOGGER.error("Error initializing user", e);
            throw e;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

