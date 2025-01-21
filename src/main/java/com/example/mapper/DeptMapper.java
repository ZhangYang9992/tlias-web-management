package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

// Mapper接口: 自动创建该接口的实现类 并创建对象放到IOC容器中
@Mapper
public interface DeptMapper {

    // 实现数据封装: 当表中属性名与实体类不一致时
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTimem})")
    void addDept(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Select("update dept set name= #{name}, update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);
}
