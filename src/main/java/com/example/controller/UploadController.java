package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    // 对IOC容器中管理的对象的使用
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 本地存储
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
//        log.info("接收参数: {}, {}, {}", name, age, file);
//
//        // 获取原始文件名
//        String originalFilename = file.getOriginalFilename();
//
//        // 新的文件名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + extension;
//
//        file.transferTo(new File("D:/images/" + newFileName));
//
//        return Result.success();
//    }


    /**
     * 阿里云OSS存储
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}", file.getOriginalFilename());

        // 将文件交给OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());

        log.info("OSS上传文件, url:[}", url);

        return Result.success(url);
    }

}
