package com.grego.userservice;

import com.grego.userservice.domain.Phone;
import com.grego.userservice.domain.User;
import com.grego.userservice.service.IUserService;
import com.grego.userservice.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;


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
		user.setCreateAt(LocalDate.now());
		user.setUpdateAt(LocalDate.now());
		user.setActive(true);
		User savedUser = userService.create(user);
		System.out.println(savedUser);
		Assertions.assertNotNull(savedUser);
		Assertions.assertNotNull(savedUser.getId());
		Assertions.assertEquals(user.getName(), savedUser.getName());


	}

}
