package info.mysuite.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class AuthenticationToken {
     
     private String username;
     private Collection<? extends GrantedAuthority> authorities;
     private String token;
     
     public AuthenticationToken(String username, Collection<? extends GrantedAuthority> collection, String token) {
          this.username = username;
          this.authorities = collection;
          this.token = token;
     }
}