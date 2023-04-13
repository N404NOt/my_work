package cn.itcast.mp;

import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// TODO ActiveRecord

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper2 {

    @Test
    public void testSelectBuId() {
        User user = new User();
        user.setId(2L);

        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("liubei");
        user.setPassword("123456");
        user.setAge(30);
        user.setName("刘备");
        user.setMail("liubei@itcast.cn");

        boolean insert = user.insert();
        System.out.println("result => " + insert);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(13L);
        user.setAge(31);

        boolean result = user.updateById();
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("user_name", "dashabi")
                .eq("user_name", "liubei");

        User user = new User();
        boolean update = user.update(wrapper);
        System.out.println("update => " + update);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setId(14L);

        boolean delete = user.deleteById();
        System.out.println(delete);
    }

    @Test
    public void testSelect() {
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",30);

        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user);
        }
    }

    /*
    *   测试全表更新，SQL分析器阻断效果
    *   拦截器，防止误操作
    *   运行直接错误，直接阻断掉了
    *   生产环境性能低不推荐使用
    * */
    @Test
    public void testUpdateAll() {
        User user = new User();
        user.setAge(31);

        boolean update = user.update(null);
        System.out.println("result>> " + update);

    }

}
