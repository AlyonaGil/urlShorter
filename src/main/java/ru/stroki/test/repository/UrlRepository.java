package ru.stroki.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.stroki.test.entity.Url;
import ru.stroki.test.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    List<Url> getUrlsByDeleteDateIsNullAndUser(User user);

    Optional<Url> findByIdAndUser(Integer id, User user);

    Optional<Url> findByShortUrl(String shortUrl);

    List<Url> getUrlsByUser(User user);

    List<Url> getUrlsByUserAndCreateDateAfter(User user, LocalDateTime date);

    @Query(value = "SELECT nextval('url_sequence')", nativeQuery = true)
    Integer getIntFromSeq();
}
