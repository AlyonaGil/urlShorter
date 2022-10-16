package ru.stroki.test.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.stroki.test.entity.Transition;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransitionRepository extends JpaRepository<Transition, Integer> {

    @Query("SELECT referer FROM Transition group by referer ORDER BY count(referer) DESC")
    List<String> getReferersTop(Pageable pageable);

    List<Transition> getTransitionByUrlAndCreateDateBetween(Url url, LocalDateTime startDate, LocalDateTime endDate);
}
