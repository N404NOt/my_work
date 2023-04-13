package cn.itcast.mp;

import cn.itcast.mp.plugins.MyInterceptor;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
// 放在这里更好，因为这个类是配置；类
@MapperScan("cn.itcast.mp.mapper") // 和 cn/itcast/mp/mapper/UserMapper.java 的 @Mapper 有一个就ok application.yml 一个就ok
public class MybatisPlusConfig {

    @Bean // 配置分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

//    @Bean // 注入自定义的拦截器
//    public MyInterceptor myInterceptor() {
//        return new MyInterceptor();
//    }

    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {

        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();

        List<ISqlParser> list = new ArrayList<>();
        list.add(new BlockAttackSqlParser()); // 全表更新、删除的阻断器
        sqlExplainInterceptor.setSqlParserList(list);

        return sqlExplainInterceptor;
    }

//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
//        return new OptimisticLockerInterceptor();
//    }
}


