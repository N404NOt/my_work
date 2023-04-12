package cn.itcast.mp;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 放在这里更好，因为这个类是配置；类
@MapperScan("cn.itcast.mp.mapper") // 和 cn/itcast/mp/mapper/UserMapper.java 的 @Mapper 有一个就ok application.yml 一个就ok
public class MybatisPlusConfig {

    @Bean // 配置分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}


