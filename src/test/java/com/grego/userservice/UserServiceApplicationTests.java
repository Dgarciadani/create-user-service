package com.grego.userservice;

import com.grego.userservice.domain.Phone;
import com.grego.userservice.domain.User;
import com.grego.userservice.domain.dto.UserReceivedDto;
import com.grego.userservice.domain.dto.UserSendDto;
import com.grego.userservice.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;


    @Autowired
    private ModelMapper modelmapper;


	@Test
	void CreateUser(){
		Phone phone = new Phone();
		phone.setCityCode("09");
		phone.setCountryCode("+52");
		phone.setNumber("8111222333");
		User user = new User();
		user.setName("Juan");
		user.setEmail("hello@gmail.com");
		user.setPassword("123456");
		user.setPhones(Set.of(phone));
		user.setCreated(LocalDate.now());
		user.setModified(LocalDate.now());
		user.setActive(true);
        user.setLastLogin(LocalDate.now());
        UserReceivedDto userReceivedDto = modelmapper.map(user, UserReceivedDto.class);
		UserSendDto savedUser = userService.create(userReceivedDto);
		System.out.println(savedUser);
		assertNotNull(savedUser);
		assertNotNull(savedUser.getId());
		//Assertions.assertEquals(user.getEmail(), savedUser.());


	}

}
