package com.example.service.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpExpr;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
////        // 1. 调用Mapper接口 查询总记录数
////        Long total = empMapper.count();
////
////        // 2. 调用Mapper接口 查询结果列表
////        Integer start = (page - 1) * pageSize;
////        List<Emp> rows = empMapper.list(start, pageSize);
////
////        // 3. 封装结果 PageResult
////        return new PageResult<Emp>(total, rows);
//
//        // PageHelper设置分页操作
//        // 1. 设置分页参数(PageHelper)
//        PageHelper.startPage(page, pageSize);
//        // 2. 执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//        // 3. 解析查询结果 并封装
//        Page<Emp> p = (Page<Emp>) empList; // Page<T>封装了结果和总数的值与函数
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }

@Override
public PageResult<Emp> page(EmpQueryParam empQueryParam) {
    // PageHelper设置分页操作
    // 1. 设置分页参数(PageHelper)
    PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
    // 2. 执行查询
    List<Emp> empList = empMapper.list(empQueryParam);
    // 3. 解析查询结果 并封装
    Page<Emp> p = (Page<Emp>) empList; // Page<T>封装了结果和总数的值与函数
    return new PageResult<Emp>(p.getTotal(), p.getResult());
}

    @Transactional // 设置事务管理
    @Override
    public void save(Emp emp) {
        // 1. 保存员工的基本信息
        // 在实现mapper前对emp中的updateTime与createTTime初始赋值
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 2. 保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            // 遍历集合，为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    // 该方法有两次操作数据库，所以需要加Transactional 即有异常均回滚
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 1. 删除员工的基本信息
        empMapper.deleteByIds(ids);

        // 2. 删除员工的工作信息
        empExprMapper.deleteByEmpIds(ids);
    }
}
