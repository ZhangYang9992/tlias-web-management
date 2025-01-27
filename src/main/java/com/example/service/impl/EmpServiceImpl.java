package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        // 1. 调用Mapper接口 查询总记录数
//        Long total = empMapper.count();
//
//        // 2. 调用Mapper接口 查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        // 3. 封装结果 PageResult
//        return new PageResult<Emp>(total, rows);

        // PageHelper设置分页操作
        // 1. 设置分页参数(PageHelper)
        PageHelper.startPage(page, pageSize);
        // 2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        // 3. 解析查询结果 并封装
        Page<Emp> p = (Page<Emp>) empList; // Page<T>封装了结果和总数的值与函数
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
}
