package kz.kaspi.stuff.security;

import kz.kaspi.stuff.dao.CredDAO;
import kz.kaspi.stuff.dao.UserDAO;
import kz.kaspi.stuff.domain.Credential;
import kz.kaspi.stuff.domain.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.catalina.servlet4preview.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("stuffUserDetailsService")
@Configurable
public class StuffUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CredDAO credDAO;

    private static final Logger log = LoggerFactory.getLogger(StuffUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.get(s);
        log.info("Authorizing User : " + user);
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        Credential credential = credDAO.getUserByToken(user.getUserId());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                credential.getPass(),
                true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()));
        log.debug("authorities : " + authorities);
        return authorities;
    }
}
