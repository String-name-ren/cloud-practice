package com.cloud.product.controller;

import com.cloud.include.utils.ESUtil;
import com.cloud.product.entity.Teacher;
import com.cloud.product.esmapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-07-31 16:16
 **/
@RequestMapping("teacher")
@RestController
public class TeacherController {

    @Autowired
    private ESUtil esUtil;

    @GetMapping("createIndex")
    public String createIndex(){
        esUtil.createIndice(TeacherMapper.index,TeacherMapper.createIndex);
        return "success";
    }


    @GetMapping("create")
    public String create(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(2L);
        teacher.setName("百岁山2");
        teacher.setSex(2);
        teacher.setCreateAt(new Date());
        return esUtil.addOrUpdateDocument(TeacherMapper.index,TeacherMapper.type,teacher);
    }

    @GetMapping("get")
    public Object get(){
        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);
        Map<String, Object> params = new HashMap<>();
        params.put("teacherId",2L);
        return esUtil.search(TeacherMapper.index,TeacherMapper.type,TeacherMapper.getPage,params,Teacher.class);
    }


}
