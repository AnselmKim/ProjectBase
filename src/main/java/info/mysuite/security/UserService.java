package info.mysuite.security;

import info.mysuite.domain.User;
import info.mysuite.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
        return userMapper.getAuthority(username);
    }
}
