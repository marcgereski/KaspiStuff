package kz.kaspi.stuff.dao;

import kz.kaspi.stuff.domain.User;
import java.util.List;

public interface UserDAO {
    List<User> getList();
    void add(User user);
    User get(Long id);
    User get(String username);
    void update(User user);
}