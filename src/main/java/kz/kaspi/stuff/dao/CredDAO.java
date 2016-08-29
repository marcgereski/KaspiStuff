package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Credential;
import kz.kaspi.stuff.domain.User;

public interface CredDAO {
    String getPass(Long userId);
    Credential getUserByToken(Long userId);
    User getUserByToken(String token);
    void add(Credential credential);
}
