package com.example.controller;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工Controller
 */
// 日志注解 Controller注解
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private EmpMapper empMapper;

    @GetMapping
    public Result page(@RequestParam(defaultValue =  "1") Integer page, @RequestParam(defaultValue =  "10") Integer pageSize) {
        log.info("分页查询: {}, {}", page, pageSize);
        PageResult<Emp> pageResult = empService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
