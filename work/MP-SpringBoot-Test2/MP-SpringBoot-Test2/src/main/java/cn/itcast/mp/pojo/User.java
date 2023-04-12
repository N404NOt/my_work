package cn.itcast.mp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String name;

    @TableField(select = false) // 查询时不返回此字段
    private Integer age;

    @TableField(value = "email") // 指定数据表中字段名
    private String mail;

    @TableField(exist = false) // 表示当前数据在数据库中不存在
    private String address; // 在数据库中不存在
}