package ru.stroki.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.stroki.test.entity.Transition;
import ru.stroki.test.entity.Url;

import java.util.List;

@Repository
public interface TransitionRepository extends JpaRepository<Transition, Integer> {

    @Query("SELECT referer\n" +
            "FROM transition\n" +
            "group by referer\n" +
            "ORDER BY count(referer) DESC " +
            "LIMIT 20")
    List<String> getReferersTop();

    List<Transition> getTransitionByUrl(Url url);
}
