package cn.itcast.mp.plugins;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;
import java.util.concurrent.Executor;

/*
*   拦截执行器的方法
*   拦截参数的处理
*   拦截结果集的处理
*   拦截sql语句构建的处理
* */

@Intercepts({@Signature(
        type= Executor.class, // 拦截执行器
        method = "update",  // 执行器中的 update方法
        args = {MappedStatement.class,Object.class}
)})
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 拦截方法, 具体业务逻辑编写的位置
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 创建 target 对象的代理对象，目的是将当前拦截器加入到该对象中
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 属性设置
        Interceptor.super.setProperties(properties);
    }
}
