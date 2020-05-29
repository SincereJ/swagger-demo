package com.example.swagger.controller;

import com.alibaba.fastjson.JSON;
import com.example.swagger.swagger.BaseController;
import com.example.swagger.swagger.annos.ApiJsonObject;
import com.example.swagger.swagger.annos.ApiJsonProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/swagger")
@RestController
@Slf4j
public class SwaggerMapController extends BaseController {

    @PostMapping("/selectIndentNumberByPrimaryIdAndName")
    @ApiJsonObject(name = "params", value = {
            @ApiJsonProperty(type = Integer.class,key = "mobile", example = "18614242538", description = "user mobile"),
            @ApiJsonProperty(type = Integer.class,key = "password", example = "123456", description = "user password"),
            @ApiJsonProperty(type = String.class,key = "NAME_SS", example = "", description = "user 姓名"),
            @ApiJsonProperty(type = Integer.class,key = "page", example = "", description = "当前页"),
            @ApiJsonProperty(type = Integer.class,key = "rows", example = "15", description = "行数")
    })
    @ApiOperation(value = "视频回放", notes = "courseLessonId 课时编号 不能为空")
    public String selectIndentNumberByPrimaryIdAndName(@RequestBody Map<String,Object> params){
        log.info("ssssssssssssss---index");


        return JSON.toJSONString(params);
    }
    @PostMapping("/otherApiInterface")
    @ApiJsonObject(name = "otherApiInterface", value = {
            @ApiJsonProperty(type = String.class,key = "NAME_SS", example = "", description = "user 姓名")
    })
    @ApiOperation(value = "otherApiInterface", notes = "otherApiInterface")
    public String otherApiInterface(@RequestBody Map<String,Object> params){
        log.info("ssssssssssssss---otherApiInterface");


        return JSON.toJSONString(params);
    }

}
