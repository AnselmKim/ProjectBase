package info.mysuite.controller;

import info.mysuite.domain.AuthenticationRequest;
import info.mysuite.domain.User;
import info.mysuite.security.AuthenticationToken;
import info.mysuite.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by anselmkim on 2017. 3. 9..
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public AuthenticationToken login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpSession session
    ) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        User user = userService.getUser(username);
        return new AuthenticationToken(user.getName(), user.getAuthorities(), session.getId());
    }
}
