package info.mysuite.repository;

import info.mysuite.domain.User;
import info.mysuite.security.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by anselmkim on 2017. 3. 8..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void getUserTest() throws Exception {
        User user = userMapper.getUser("admin");
        assertThat("admin", is(user.getUsername()));
        assertThat("admin", is(user.getName()));
        assertThat("1234", userService.passwordEncoder().matches("1234", user.getPassword()), is(true));
    }

    @Test
    public void readAuthorityTest() {

        List<GrantedAuthority> authorities = userMapper.getAuthority("admin");
        Iterator<GrantedAuthority> it = authorities.iterator();

        it.forEachRemaining(grantedAuthority ->
                assertThat(authorities, hasItem(new SimpleGrantedAuthority(grantedAuthority.getAuthority())))
        );
    }
}
