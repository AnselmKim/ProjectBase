package info.mysuite.repository;

import info.mysuite.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void getUserTest() throws Exception {
        User user = userMapper.getUser("cusonar");
        assertThat("cusonar", is(user.getUsername()));
        assertThat("YCU", is(user.getName()));
        assertThat("1234", is(user.getPassword()));
    }

    @Test
    public void readAuthorityTest() {
        List<String> authorities = userMapper.getAuthority("cusonar");
        assertThat(authorities, hasItems("ADMIN", "USER"));

        authorities= userMapper.getAuthority("abc");
        assertThat(authorities, hasItem("USER"));
    }
}
