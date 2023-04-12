package cn.itcast.mp;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInsert {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setMail("@sanguo.qq.com111");
        user.setName("曹操1112222");
        user.setAge(12122);
        user.setPassword("111222333");
        user.setUserName("caocao");
        user.setAddress("aaaaaa");
        int insert = this.userMapper.insert(user);

        System.out.println("insert >>> " + insert);
        System.out.println(user.getId());
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L);
        user.setAge(998);
        user.setPassword("汤臣一品");

        int result = userMapper.updateById(user);
        System.out.println(result);
    }


    @Test
    public void testUpdate() {
        User user1 = new User();
        user1.setAge(20);
        user1.setPassword("88888888");

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","zhangsan"); // 匹配user_name = zhangsan 的用户数据

        int result  = userMapper.update(user1,wrapper);
        System.out.println(result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",12).set("password","9999999") // 更新字段
                .eq("user_name","zhangsan"); // 更新条件

        // 根据条件做更新
        int update = userMapper.update(null, wrapper);
        System.out.println(update);
    }

    @Test
    public void testDeleteById() {

        int result = userMapper.deleteById(9L);
        System.out.println("result>>> " + result);
    }

    @Test
    public void testDeleteByMap() {

        Map<String,Object> map = new HashMap<>();
        map.put("user_name","zhangsan");
        // and 关系
//        map.put("password","123456");

        int result = userMapper.deleteByMap(map);
        System.out.println("result>>> " + result);

    }

    @Test
    public void testDelete() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","caocao")
                .eq("password","111222333");

        int result = userMapper.delete(wrapper);
        System.out.println("result>>> " + result);
    }

    @Test
    public void testDelete2() {
        User user = new User();
        user.setUserName("lisi");
        user.setPassword("123456");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int result = userMapper.delete(wrapper);
        System.out.println("result>>> " + result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(10, 11));
        System.out.println("result>>> " + result);
    }

    @Test
    public void testSelectBatchIDs() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2, 4, 5, 6));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi")
                .eq("password","123456");
        // 查询的数据超过一条，会抛出异常
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20); // 条件：年龄大于20岁的用户
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("count>>> " + count);
    }

    @Test
    public void testSelectCount2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age",20)
                .eq("age",29);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("count>>> " + count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "itcast");

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    // 测试分页查询
    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1,2); // 查询第一页，查询1条数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email","itcast");

        Page<User> iPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数： " + iPage.getTotal());
        System.out.println("数据总页数: " + iPage.getPages());
        System.out.println("当前页数: " +iPage.getCurrent());

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }
}
