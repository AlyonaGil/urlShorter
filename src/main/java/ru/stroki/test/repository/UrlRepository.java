package ru.stroki.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stroki.test.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {

}
