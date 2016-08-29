package kz.kaspi.stuff.security;

import kz.kaspi.stuff.dao.UserDAO;
import kz.kaspi.stuff.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class SecurityController {
    @Autowired
    public UserDAO userDAO;

    @RequestMapping({"user", "/me"})
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());

        User user = userDAO.get(principal.getName());
        Long id = user != null ? user.getUserId() : -1L;
        map.put("id", id.toString());
        return map;
    }
}
