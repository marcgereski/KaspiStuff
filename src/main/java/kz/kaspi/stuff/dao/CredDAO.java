package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Credential;

public interface CredDAO {
    String getPass(Long userId);
    Credential get(Long userId);
    void add(Credential credential);
}
