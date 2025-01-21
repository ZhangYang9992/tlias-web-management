package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门
     * @return
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     * @return
     */
    void addDept(Dept dept);

    /**
     * 根据id查询部门即可
     * @return
     */
    Dept getById(Integer id);

    /**
     * 修改部门
     * @return
     */
    void updateDept(Dept dept);
}
