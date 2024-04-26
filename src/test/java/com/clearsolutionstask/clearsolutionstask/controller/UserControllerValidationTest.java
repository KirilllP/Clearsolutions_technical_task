package com.clearsolutionstask.clearsolutionstask.controller;

import com.clearsolutionstask.clearsolutionstask.dto.DateRangeDto;
import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import com.clearsolutionstask.clearsolutionstask.exception.customException.WrongIdException;
import com.clearsolutionstask.clearsolutionstask.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerValidationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	public ObjectMapper objectMapper;

	@MockBean
	private UserServiceImpl service;

	@Test
	void successfulValidationAndSavingTest() throws Exception {
		UserDto inputUser = UserDto.builder().email("mrsad999@gmail.com").firstName("sadsad").lastName("nothappy").birthDate(LocalDate.of(2000, 4, 25)).build();

		UserDto outputUser = UserDto.builder().id(1).email("mrsad999@gmail.com").firstName("sadsad").lastName("nothappy").birthDate(LocalDate.of(2000, 4, 25)).build();

		when(service.saveUser(inputUser)).thenReturn(outputUser);

		mvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void validationExceptionOnSavingIncorrectDataTest() throws Exception {
		UserDto inputUser = generateUser();
		inputUser.setEmail("13");
		mvc.perform(MockMvcRequestBuilders.post("/user/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		inputUser = generateUser();
		inputUser.setBirthDate(LocalDate.of(2020, 4, 25));
		mvc.perform(MockMvcRequestBuilders.post("/user/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		inputUser = generateUser();
		inputUser.setFirstName(null);
		mvc.perform(MockMvcRequestBuilders.post("/user/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		inputUser = generateUser();
		inputUser.setLastName(null);
		mvc.perform(MockMvcRequestBuilders.post("/user/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void successfulDeletingTest() throws Exception {
		doNothing().when(service).deleteUser(anyInt());
		mvc.perform(MockMvcRequestBuilders.delete("/user/delete/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void wrongIdPassedInDeletingTest() throws Exception {
		doThrow(WrongIdException.class).when(service).deleteUser(anyInt());
		mvc.perform(MockMvcRequestBuilders.delete("/user/delete/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void patchSuccessfulTest() throws Exception {

		PatchUserDto patchUserDto = PatchUserDto.builder().email("mrsad999@gmail.com").birthDate(LocalDate.of(2001, 4, 25)).build();

		UserDto outputUser = UserDto.builder().id(1).email("mrsad999111111@gmail.com").firstName("sadsad").lastName("nothappy").birthDate(LocalDate.of(2001, 4, 25)).build();

		when(service.patchUser(Integer.valueOf(1), patchUserDto)).thenReturn(outputUser);

		mvc.perform(MockMvcRequestBuilders.post("/user/patch/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patchUserDto))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void patchValidationExceptionTest() throws Exception {

		PatchUserDto patchUserDto = PatchUserDto.builder().email("mrsad999").birthDate(LocalDate.of(2001, 4, 25)).build();

		UserDto outputUser = UserDto.builder().id(1).email("mrsad999111111@gmail.com").firstName("sadsad").lastName("nothappy").birthDate(LocalDate.of(2001, 4, 25)).build();

		when(service.patchUser(Integer.valueOf(1), patchUserDto)).thenReturn(outputUser);

		mvc.perform(MockMvcRequestBuilders.post("/user/patch/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patchUserDto))).andExpect(MockMvcResultMatchers.status().isBadRequest());

		patchUserDto = PatchUserDto.builder().email("mrsad999@gmail.com").birthDate(LocalDate.of(2020, 4, 25)).build();

		mvc.perform(MockMvcRequestBuilders.post("/user/patch/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patchUserDto))).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void putValidationExceptionTest() throws Exception {

		UserDto inputUser = generateUser();
		inputUser.setEmail("13");
		mvc.perform(MockMvcRequestBuilders.post("/user/put/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		inputUser = generateUser();
		inputUser.setBirthDate(LocalDate.of(2020, 4, 25));
		mvc.perform(MockMvcRequestBuilders.post("/user/put/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		inputUser = generateUser();
		inputUser.setFirstName(null);
		mvc.perform(MockMvcRequestBuilders.post("/user/put/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		inputUser = generateUser();
		inputUser.setLastName(null);
		mvc.perform(MockMvcRequestBuilders.post("/user/put/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void putSuccessfulCaseTest() throws Exception {

		UserDto inputUser = generateUser();

		UserDto outputUser = UserDto.builder().id(1).email("mrsad999111111@gmail.com").firstName("sadsad").lastName("nothappy").birthDate(LocalDate.of(2001, 4, 25)).build();

		when(service.putUser(Integer.valueOf(1), inputUser)).thenReturn(outputUser);

		mvc.perform(MockMvcRequestBuilders.post("/user/put/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(inputUser)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void inRangeValidationExceptionTest() throws Exception {

		DateRangeDto dateRangeDto = DateRangeDto.builder().from(LocalDate.of(2001, 4, 25)).to(LocalDate.of(2000, 4, 25)).build();

		mvc.perform(MockMvcRequestBuilders.post("/user/range").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dateRangeDto))).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void inRangeSuccessfulValidationTest() throws Exception {

		DateRangeDto dateRangeDto = DateRangeDto.builder().from(LocalDate.of(2000, 4, 25)).to(LocalDate.of(2001, 4, 25)).build();

		mvc.perform(MockMvcRequestBuilders.post("/user/range").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dateRangeDto))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	private static UserDto generateUser() {
		return UserDto.builder().email("mrsad999@gmail.com").firstName("sadsad").lastName("nothappy").birthDate(LocalDate.of(2000, 4, 25)).build();
	}

}
