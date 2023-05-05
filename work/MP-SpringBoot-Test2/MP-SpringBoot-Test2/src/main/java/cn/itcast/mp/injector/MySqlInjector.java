package cn.itcast.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.ArrayList;
import java.util.List;

// 此时还没有加入 Spring 中进行管理, 在 MybatisPlusConfig 中@Bean 注入

// AbstractSqlInjector
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        List<AbstractMethod> list = new ArrayList<>();

        // 获取父类中的集合
        list.addAll(super.getMethodList(mapperClass));
        // 再扩充自定义的方法
        list.add(new FindAll());

        return list;
    }
}
