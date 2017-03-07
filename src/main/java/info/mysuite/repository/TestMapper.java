package info.mysuite.repository;

import info.mysuite.domain.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by anselmkim on 2017. 3. 7..
 */
@Mapper
public interface TestMapper {
    Test selectTest(@Param("name") String name);
}
