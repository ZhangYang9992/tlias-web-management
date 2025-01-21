package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    // 注意引入的是slf4j中的Logger
//    private static final Logger  log = LoggerFactory.getLogger(DeptController.class); // 固定格式 类的字节码

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    // RequestMapping的衍生注解
    @GetMapping("/depts")
    public Result getDeptList(){
//        System.out.println("查询全部部门的数据");
        log.info("查询全部部门的数据");
        // 调用服务层放放风 通过@Autowired来自动管理
        List<Dept> depetList = deptService.findAll();

        return Result.success(depetList);
    }

//    方法一：不推荐传参方式 因为繁琐需要类型转换
//    @DeleteMapping("/depts")
//    public Result deleteDept(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println(id);
//        return Result.success();
//    }

//    @DeleteMapping("/depts")
//    public Result deleteDept(@RequestParam("id") Integer id){
//        System.out.println("根据ID删除部门:" + id);
//        return Result.success();
//    }

    @DeleteMapping("/depts")
    public Result deleteDept(Integer id){
//        System.out.println("根据ID删除部门:" + id);
        log.info("根据ID删除部门: {}", id); // 通过占位符{}传参
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept){
//        System.out.println("新增部门:"+dept);
        log.info("新增部门: {}", dept);
        deptService.addDept(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getDept(@PathVariable("id") Integer id){
//        System.out.println("根据ID查询部门:" + id);
        log.info("根据ID查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept){
//        System.out.println("设置ID部门:" + dept);
        log.info("设置ID部门: {}", dept);
        deptService.updateDept(dept);
        return Result.success();
    }
}
