package ru.stroki.test.urlShorter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.stroki.test.controller.UserController;
import ru.stroki.test.dto.RegUserDto;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.repository.UserRepository;
import ru.stroki.test.services.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DtoMapper dtoMapper;

    public void deleteUser(String login) {
        userRepository.deleteByLogin(login);
    }

    private RegUserDto getTestUser(String login, String password) {
        return RegUserDto.builder()
                .login(login)
                .password(password)
                .build();
    }

    @Test
    public void givenUser_whenAdd_thenStatus201UserAndLocationReturned() throws Exception {
        String login = "test_user";
        String password = "test_password";
        mockMvc.perform(
                        post("/urlShorter/user")
                                .content(objectMapper.writeValueAsString(getTestUser(login, password)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value(login));
        deleteUser(login);
    }
}
