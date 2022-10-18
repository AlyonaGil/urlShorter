package ru.stroki.test.utils;

import lombok.experimental.UtilityClass;

import java.util.Base64;

@UtilityClass
public final class AuthUtil {

    public String getHash(String login, String password){
        String hash = login + ":" + password;
        return Base64.getEncoder().encodeToString(hash.getBytes());
    }

    public String decodeHash(String hash){
        byte[] decodedBytes = Base64.getDecoder().decode(hash);
        return new String(decodedBytes);
    }

    public String getLogin(String hash){
        return AuthUtil.decodeHash(hash).split(":")[0];
    }

    public String getPassword(String hash){
        return AuthUtil.decodeHash(hash).split(":")[1];
    }
}
