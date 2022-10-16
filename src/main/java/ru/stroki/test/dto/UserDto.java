package ru.stroki.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stroki.test.entity.User;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String login;
    private LocalDateTime registrationDate;

    public UserDto(User user){
        this.setId(user.getId());
        this.setRegistrationDate(user.getCreateDate());
        this.setLogin(user.getLogin());
    }
}
