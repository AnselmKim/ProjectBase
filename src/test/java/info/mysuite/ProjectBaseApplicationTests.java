package info.mysuite;

import info.mysuite.domain.User;
import info.mysuite.repository.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectBaseApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void getUserTest() throws Exception {
		User user = userMapper.getUser("cusonar");
		assertThat("cusonar", is(user.getUsername()));
		assertThat("YCU", is(user.getName()));
		assertThat("1234", is(user.getPassword()));
	}

}
