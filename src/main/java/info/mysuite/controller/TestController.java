package info.mysuite.controller;

import info.mysuite.domain.Test;
import info.mysuite.repository.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anselmkim on 2017. 3. 7..
 */
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/{name}")
    public Test test(@PathVariable String name) {
        return testMapper.selectTest(name);
    }
}
