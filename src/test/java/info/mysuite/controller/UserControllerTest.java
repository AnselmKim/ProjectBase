package info.mysuite.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.mysuite.domain.AuthenticationRequest;
import info.mysuite.domain.User;
import info.mysuite.security.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
     
     @Autowired
     private WebApplicationContext wac;

     @Autowired
     private UserService userService;

     private MockMvc mvc;
     private User user;
     
     @Before
     public void setup() {

          //유저생성
          user = new User();
          user.setUsername("user");
          user.setPassword("1234");
          user.setAccountNonExpired(true);
          user.setAccountNonLocked(true);
          user.setName("user");
          user.setCredentialsNonExpired(true);
          user.setEnabled(true);
          user.setAuthorities(AuthorityUtils.createAuthorityList("USER"));

          userService.createUser(user);

          mvc = MockMvcBuilders
                    .webAppContextSetup(wac)
                    .build();
     }

     @After
     public void tearDown() throws Exception {
          userService.deleteUser(user.getUsername());
     }

     @Test
     public void loginTest() throws Exception {
          AuthenticationRequest request = new AuthenticationRequest();
          request.setUsername("user");
          request.setPassword("1234");
          
          ObjectMapper om = new ObjectMapper();
          
          mvc.perform(post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(om.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(jsonPath("$.username", is(request.getUsername())))
               .andExpect(jsonPath("$.authorities[*].authority", hasItem("USER")));
     }
}