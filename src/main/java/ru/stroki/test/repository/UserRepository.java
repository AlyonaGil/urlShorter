package ru.stroki.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import ru.stroki.test.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
@Validated
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByLogin(@NotNull @NotBlank String login);
    void deleteByLogin(@NotNull @NotBlank String login);
}
