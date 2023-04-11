package cn.itcast.mp;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInsert {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setEmail("@sanguo.qq.com");
        user.setName("曹操");
        user.setAge(12);
        user.setPassword("111222333");
        user.setUserName("caocao");
        int insert = this.userMapper.insert(user);

        System.out.println("insert >>> " + insert);
        System.out.println(user.getId());
    }
}
