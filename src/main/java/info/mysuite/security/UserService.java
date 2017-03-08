package info.mysuite.security;

import info.mysuite.domain.User;
import info.mysuite.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by anselmkim on 2017. 3. 8..
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUser(username);
        user.setAuthorities(getAuthorities(username));
        return user;
    }

    private Collection<GrantedAuthority> getAuthorities(String username) {
        return userMapper.getAuthority(username);
    }

    public User getUser(String username) {
        User user = userMapper.getUser(username);
        user.setAuthorities(getAuthorities(username));
        return user;
    }

    public void createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.createUser(user);
        userMapper.createAuthority(user);
    }

    public void deleteUser(String username) {
        userMapper.deleteUser(username);
        userMapper.deleteAuthority(username);
    }

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
