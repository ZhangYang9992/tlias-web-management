package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> exprList);
}
