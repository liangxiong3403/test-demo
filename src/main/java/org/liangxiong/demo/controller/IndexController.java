package org.liangxiong.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2020-09-07 21:36
 * @description 主页控制器
 **/
@Api(tags = "主页控制器")
@RestController
@RequestMapping("/index")
public class IndexController {

    /**
     * hello 方法
     *
     * @param username 用户名
     * @return
     */
    @ApiOperation(value = "欢迎页面", httpMethod = "GET")
    @GetMapping("/hello")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名"), @ApiImplicitParam(name = "age", value = "年龄")})
    public String hello(String username, Integer age) {
        return "swagger say: hello: " + username + ":" + age;
    }
}
