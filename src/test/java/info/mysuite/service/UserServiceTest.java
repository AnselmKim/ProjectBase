package info.mysuite.service;

import info.mysuite.domain.User;
import info.mysuite.security.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Created by anselmkim on 2017. 3. 8..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername("user");
        user.setPassword("1234");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setName("user");
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
    }

//    @After
//    public void tearDown() throws Exception {
//        userService.deleteUser(user.getUsername());
//    }

    @Test
    public void createUserTest() throws Exception {
        userService.createUser(user);
        User userInfo = userService.getUser(user.getUsername());
        assertEquals("username is not match!", user.getUsername(), userInfo.getUsername());

        PasswordEncoder passwordEncoder = userService.passwordEncoder();
        assertThat(passwordEncoder.matches("1234", user.getPassword()), is(true));

        user.getAuthorities().forEach(authority ->
                assertThat((Collection<GrantedAuthority>) userInfo.getAuthorities(),hasItem(new SimpleGrantedAuthority(authority.getAuthority())))
        );
    }
}
