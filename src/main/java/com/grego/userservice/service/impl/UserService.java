package com.grego.userservice.service.impl;


import com.grego.userservice.domain.User;
import com.grego.userservice.domain.UserRoles;
import com.grego.userservice.domain.dto.PhoneReceivedDto;
import com.grego.userservice.domain.dto.UserReceivedDto;
import com.grego.userservice.domain.dto.UserSendDto;
import com.grego.userservice.exceptions.EmailAlreadyRegisteredException;
import com.grego.userservice.exceptions.ResourceNotFoundException;
import com.grego.userservice.repository.IUserRepository;
import com.grego.userservice.security.jwt.JwtTokenUtil;
import com.grego.userservice.service.IPhoneService;
import com.grego.userservice.service.IUserService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService<UserReceivedDto>, UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;
    private final IPhoneService phoneService;

    private final ModelMapper modelMapper;

    private JwtTokenUtil jwtUtil;


    @Override
    @Transactional(rollbackOn = ConstraintViolationException.class)
    public UserSendDto create(UserReceivedDto user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email already registered");
        } else {
            try {
                LOGGER.info("Creating user");
                User userEntity = modelMapper.map(user, User.class);
                userEntity = this.initializer(userEntity);
                LOGGER.info(userEntity.toString());
                User savedUser = userRepository.save(userEntity);
                LOGGER.info("User saved");
                List<PhoneReceivedDto> phones = user.getPhones().stream().peek(phone -> phone.setUser(savedUser)).toList();
                phoneService.saveAll(phones);
                LOGGER.info("User and phone created");
                UserSendDto map = modelMapper.map(savedUser, UserSendDto.class);
                String jwt = jwtUtil.generateToken(savedUser);
                map.setToken(jwt);
                this.saveToken(savedUser.getEmail(), jwt);
                LOGGER.info("jwt generated");
                return map;

            } catch (Exception e) {
                throw new RuntimeException("Error creating user", e);
            }
        }
    }

    //this method is to proof that modifying the user entity in the repository is not required
    @Override
    public UserSendDto disableUser(String email) {
        if (this.existsByEmail(email)) {
            try {
                LOGGER.info("Disabling user");
                LocalDate nowDate = LocalDate.now();
                userRepository.disableUserByEmail(email, nowDate);
                LOGGER.info("User disabled");
                return this.findUserByEmail(email);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }


    @Override
    public UserSendDto findUserByEmail(String email) {
        if(this.existsByEmail(email)) {
            try {
                LOGGER.info("Finding user by email");
                LOGGER.debug("email: {}", email);
                Optional<User> user = userRepository.findUserByEmail(email);
                LOGGER.info("User found");
                LOGGER.debug("user: {}", user);
                UserSendDto userSend = modelMapper.map(user, UserSendDto.class);
                return userSend;
            } catch (Exception e) {
                throw new RuntimeException("Error finding user", e);
            }
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    //support method for create and update
    @Override
    public boolean existsByEmail(String email) {
        LOGGER.info("Checking if user exists by email");
        LOGGER.debug("email: {}", email);
        boolean exists = userRepository.existsByEmail(email);
        LOGGER.info("User exists: {}", exists);
        return exists;
    }


    //use this method to save the token in the database after the user is created and saved
    @Override
    public void saveToken(String email, String token) {
        LOGGER.info("Saving token");
        userRepository.updateUserTokenByEmail(email, token);
        LOGGER.info("Token saved");
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
            user.setUserRole(UserRoles.USER);
            user.setIsactive(true);
            LOGGER.info("User initialized");
            return user;
        } catch (Exception e) {
            LOGGER.error("Error initializing user", e);
            throw e;
        }
    }


    //required for userDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

