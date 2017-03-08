package info.mysuite.repository;

import info.mysuite.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by anselmkim on 2017. 3. 7..
 */
@Mapper
public interface UserMapper {
    User getUser(String username);
    List<GrantedAuthority> getAuthority(String username);
}
