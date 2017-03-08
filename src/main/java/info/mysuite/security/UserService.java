package info.mysuite.security;

import info.mysuite.domain.User;
import info.mysuite.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by anselmkim on 2017. 3. 8..
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUser(username);
        user.setAuthorities(getAuthorities(username));
        return user;
    }

    private Collection<GrantedAuthority> getAuthorities(String username) {
        List<String> tmp_authorities = userMapper.getAuthority(username);
        List<GrantedAuthority> authorities = new ArrayList<>();

        tmp_authorities.forEach(authority ->
            authorities.add(new SimpleGrantedAuthority(authority))
        );
        return authorities;
    }
}
