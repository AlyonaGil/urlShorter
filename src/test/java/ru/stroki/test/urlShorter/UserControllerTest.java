package ru.stroki.test.urlShorter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.stroki.test.dto.RegUserDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.UserRepository;
import ru.stroki.test.utils.AuthUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private UserRepository userRepository;

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
    public void givenUser_whenAdd_thenStatus201UserReturned() throws Exception {
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

    @Test
    public void givenUser_whenAdd_thenStatus418LoginNull() throws Exception {
        String login = "";
        String password = "test_password";
        mockMvc.perform(
                        post("/urlShorter/user")
                                .content(objectMapper.writeValueAsString(getTestUser(login, password)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isIAmATeapot())
                .andExpect(jsonPath("$.message").value("createUser.regUserDto.login: must not be blank"));
    }

    @Test
    public void givenUser_whenAdd_thenStatus418PasswordNull() throws Exception {
        String login = "test_user";
        String password = "";
        mockMvc.perform(
                        post("/urlShorter/user")
                                .content(objectMapper.writeValueAsString(getTestUser(login, password)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isIAmATeapot())
                .andExpect(jsonPath("$.message").value("createUser.regUserDto.password: must not be blank"));
    }

    @Test
    public void givenUser_whenAdd_thenStatus418LoginLengthShort() throws Exception {
        String login = "user";
        String password = "test_password";
        mockMvc.perform(
                        post("/urlShorter/user")
                                .content(objectMapper.writeValueAsString(getTestUser(login, password)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isIAmATeapot())
                .andExpect(jsonPath("$.message").value("Длина логина должна быть от 5 до 25 символов"));
    }

    @Test
    public void givenUser_whenAdd_thenStatus418PasswordLengthShort() throws Exception {
        String login = "test_user";
        String password = "test";
        mockMvc.perform(
                        post("/urlShorter/user")
                                .content(objectMapper.writeValueAsString(getTestUser(login, password)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isIAmATeapot())
                .andExpect(jsonPath("$.message").value("Длина пароля должна быть от 5 до 25 символов"));
    }

    @Test
    public void givenUser_whenAdd_thenStatus418LoginLengthLong() throws Exception {
        String login = "test_user_with_long_string";
        String password = "test_password";

        mockMvc.perform(
                        post("/urlShorter/user")
                                .content(objectMapper.writeValueAsString(getTestUser(login, password)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isIAmATeapot())
                .andExpect(jsonPath("$.message").value("Длина логина должна быть от 5 до 25 символов"));
    }

    @Test
    public void givenUser_whenAdd_thenStatus418PasswordLengthLong() throws Exception {
        String login = "test_user";
        String password = "test_password_with_long_string";
        mockMvc.perform(
                        post("/urlShorter/user")
                                .content(objectMapper.writeValueAsString(getTestUser(login, password)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isIAmATeapot())
                .andExpect(jsonPath("$.message").value("Длина пароля должна быть от 5 до 25 символов"));
    }

    @Test
    public void givenUser_whenGetInfo_thenStatus200UserReturned() throws Exception{
        String login = "test_user";
        String password = "test_password";
        User user = User.builder()
                .login(login)
                .hash(AuthUtil.getHash(login, password))
                .build();
        User userSaved = userRepository.saveAndFlush(user);
        mockMvc.perform(
                        get("/urlShorter/user")
                                .header(HttpHeaders.AUTHORIZATION, "Basic " + userSaved.getHash())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(login));
        deleteUser(login);
    }

    @Test
    public void givenUser_whenGetInfo_thenStatus401Unauthorized() throws Exception{
        String login = "test_user";
        String password = "test_password";
        User user = User.builder()
                .login(login)
                .hash(AuthUtil.getHash(login, password))
                .build();
        User userSaved = userRepository.saveAndFlush(user);
        mockMvc.perform(
                        get("/urlShorter/user")
                                .header(HttpHeaders.AUTHORIZATION, "Basic 1234")
                )
                .andExpect(status().isUnauthorized());
        deleteUser(login);
    }
}
