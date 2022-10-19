package ru.stroki.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.stroki.test.entity.Transition;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransitionRepository extends JpaRepository<Transition, Integer> {
    @Query(value = "SELECT referer\n" +
            "FROM transition\n" +
            "WHERE url_id IN (SELECT id\n" +
            "                FROM url\n" +
            "                WHERE user_id = :userID)\n" +
            "GROUP BY referer\n" +
            "ORDER BY count(referer) DESC\n" +
            "LIMIT 20", nativeQuery = true)
    List<String> getReferersTop(@Param("userID") Integer userId);

    List<Transition> getTransitionByUrlAndCreateDateBetween(Url url, LocalDateTime startDate, LocalDateTime endDate);
}
