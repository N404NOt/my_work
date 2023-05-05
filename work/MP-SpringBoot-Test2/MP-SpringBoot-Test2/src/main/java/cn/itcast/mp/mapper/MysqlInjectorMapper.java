package cn.itcast.mp.mapper;

import cn.itcast.mp.injector.MySqlInjector;
import cn.itcast.mp.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MysqlInjectorMapper extends MyBaseMapper<User> {

}
