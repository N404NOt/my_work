package cn.itcast.mp;

import cn.itcast.mp.mapper.MyBaseMapper;
import cn.itcast.mp.mapper.MysqlInjectorMapper;
import cn.itcast.mp.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper3 {

//    @Autowired
//    private MyBaseMapper myBaseMapper;
//
//    @Test
//    public void testFindAll() {
//        List<User> all = this.myBaseMapper.findAll();
//        for (User user : all) {
//            System.out.println(user);
//        }
//    }

    @Resource
    private MysqlInjectorMapper mysqlInjectorMapper;

    @Test
    public void testFindAll2() {
        List<User> all = mysqlInjectorMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() {
        User user = mysqlInjectorMapper.selectById(2L);
        System.out.println(user);
    }

}
