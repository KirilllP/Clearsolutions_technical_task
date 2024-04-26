package com.clearsolutionstask.clearsolutionstask.controller;


import com.clearsolutionstask.clearsolutionstask.dto.DateRangeDto;
import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import com.clearsolutionstask.clearsolutionstask.entity.User;
import com.clearsolutionstask.clearsolutionstask.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.util.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureEmbeddedDatabase(provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
//@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("User Controller API tests")
public class UserConrollerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    private static LocalDate generateRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2004, 4, 26).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

    @BeforeAll
    public void setup() {
        userRepository.deleteAll();
        initData();

        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private void initData() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(User.builder()
                    .firstName(RandomStringUtils.randomAlphabetic(5))
                    .lastName(RandomStringUtils.randomAlphabetic(5))
                    .email(RandomStringUtils.randomAlphabetic(5) + "@gmail.com")
                    .birthDate(generateRandomDate())
                    .build());
        }
        userRepository.saveAll(users);
    }

    @Test
    void successfulValidationAndSavingTest() throws Exception {

        UserDto inputUser = UserDto.builder().email("mrsad999@gmail.com").firstName("sadsad").lastName("nothappy").birthDate(LocalDate.of(2000, 4, 25)).build();

        mvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputUser)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(11));
    }

    @Test
    void successfulPatchingUser() throws Exception {

        PatchUserDto patchUserDto = PatchUserDto.builder().email("mrsad999@gmail.com").birthDate(LocalDate.of(2001, 4, 25)).build();

        mvc.perform(MockMvcRequestBuilders.post("/user/patch/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchUserDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.email").value("mrsad999@gmail.com"))
                .andExpect(jsonPath("$.birthDate").value("2001-04-25"));
    }

    @Test
    void successfulDeletingTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/user/delete/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deletingNotExistingUserTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/user/delete/500")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void putSuccessfulCaseTest() throws Exception {

        UserDto inputUser = UserDto.builder()
                .email("mrsad999@gmail.com")
                .firstName("sadsad")
                .lastName("nothappy")
                .birthDate(LocalDate.of(2000, 4, 25)).build();

        mvc.perform(MockMvcRequestBuilders.post("/user/put/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.email").value("mrsad999@gmail.com"))
                .andExpect(jsonPath("$.birthDate").value("2000-04-25"));
    }

    @Test
    void inRangeSuccessfulValidationTest() throws Exception {

        DateRangeDto dateRangeDto = DateRangeDto.builder().from(LocalDate.of(1970, 1, 1)).to(LocalDate.of(2004, 4, 26)).build();

        mvc.perform(MockMvcRequestBuilders.post("/user/range").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dateRangeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value((10)));

    }

}