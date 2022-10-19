package ru.stroki.test.utils;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import ru.stroki.test.repository.UrlRepository;

import javax.validation.constraints.NotNull;

@UtilityClass
public final class UrlConverter {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();
    private final int base = allowedCharacters.length;

    public String getShortUrl(@NotNull Integer id){
        StringBuilder shortUrl = new StringBuilder();
        while (id > 0) {
            shortUrl.append(allowedCharacters[id % base]);
            id = id / base;
        }
        return shortUrl.reverse().toString();
    }
}
