package cn.itcast.mp;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

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
        wrapper.eq("user_name", "zhangsan"); // 匹配user_name = zhangsan 的用户数据

        int result = userMapper.update(user1, wrapper);
        System.out.println(result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age", 12).set("password", "9999999") // 更新字段
                .eq("user_name", "zhangsan"); // 更新条件

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

        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "zhangsan");
        // and 关系
//        map.put("password","123456");

        int result = userMapper.deleteByMap(map);
        System.out.println("result>>> " + result);

    }

    @Test
    public void testDelete() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "caocao")
                .eq("password", "111222333");

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
        wrapper.eq("user_name", "lisi")
                .eq("password", "123456");
        // 查询的数据超过一条，会抛出异常
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20); // 条件：年龄大于20岁的用户
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("count>>> " + count);
    }

    @Test
    public void testSelectCount2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age", 20)
                .eq("name", "caocao");
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
        Page<User> page = new Page<>(1, 2); // 查询第一页，查询1条数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "itcast");

        Page<User> iPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数： " + iPage.getTotal());
        System.out.println("数据总页数: " + iPage.getPages());
        System.out.println("当前页数: " + iPage.getCurrent());

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void testFindById() {
        User user = userMapper.findByIdd(2L);
        System.out.println(user);
    }

    @Test
    public void testAllEq() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", 20);
        map.put("password", null);

        // TODO
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        //  SELECT id,user_name,password,name,email AS mail FROM tb_user WHERE (password IS NULL AND name = ? AND age = ?)
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("----------------------------------------------");

        // TODO  这里的 flase 参数是判断到哪个参数为 null 的时候构造sql直接忽视掉 null is NULL
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        // 这里的 flase 参数是判断到哪个参数为 null 的时候构造sql直接忽视掉 null is NULL
        wrapper2.allEq(map, false);
        // SELECT id,user_name,password,name,email AS mail FROM tb_user WHERE (name = ? AND age = ?)
        List<User> users2 = userMapper.selectList(wrapper2);
        for (User user : users2) {
            System.out.println(user);
        }
        System.out.println("----------------------------------------------");

        // TODO 过滤
        // SELECT id,user_name,password,name,email AS mail FROM tb_user WHERE (age = ?)
        QueryWrapper<User> wrapper3 = new QueryWrapper<>();
        wrapper3.allEq((k,v) -> (k.equals("age") || k.equals("id")),map);
        List<User> users3 = userMapper.selectList(wrapper3);
        for (User user : users3) {
            System.out.println(user);
        }
    }

    // TODO 查询
    @Test
    public void testEq() {
        // eq : 相等
        // ne : 不等于
        // gt : 大于
        // ge : 大于等于
        // lt : 小于
        // le : 小于等于
        // between : 在之间 a AND b
        // notBetween : 不在之间
        // in : IN ( )
        // notIn : NOT IN (v0,v1)

        // like : %值%
        // notLike :  not like %值%
        // likeLeft : name like 值%
        // likeRight : name like %值

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.in("name","李四","王五","赵六")
                .lt("age","30")
                .between("age",10,30);

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }


    // TODO 排序
    // orderBy("id","age")   -> order by id ASC ,age ASC
    // orderByAsc 升序
    // oderByDesc 降序
    @Test
    public void testOrderById() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","lisi")
                .or()
                .eq("age",21);
        // SELECT id,user_name,password,name,email AS mail FROM tb_user WHERE (name = ? OR age = ?)
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","lisi")
                .or()
                .eq("age",21)
                .select("id","name");
        // SELECT id,name FROM tb_user WHERE (name = ? OR age = ?)
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
