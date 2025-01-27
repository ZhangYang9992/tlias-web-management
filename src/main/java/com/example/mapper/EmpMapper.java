package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")

    /**
     * 分页查询
     * @return
     */    public Long count();
//   原始实现方式
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    // PageHelper实现方式
    // 非条件查询
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
    //    "order by e.update_time desc")

    // 条件查询较复杂 可以用xml来配置实现
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    public List<Emp> list(EmpQueryParam empQueryParam);

    // 在pageHeler定义下只需要定义下述语句即可
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
    //        "order by e.update_time desc")
    // public List<Emp> list();

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取生成的主键 —— 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values(#{username}, #{name}), #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime}")
    // 注意类中的变量名是驼峰命名
    void insert(Emp emp);
}
