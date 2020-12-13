package org.liangxiong.demo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.liangxiong.demo.annotation.ResultResponseBody;
import org.liangxiong.demo.constants.ResultStatus;
import org.liangxiong.demo.exception.ResultException;
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

    /**
     * 获取json数据
     *
     * @return
     */
    @ResultResponseBody
    @ApiOperation(value = "获取json数据", httpMethod = "GET")
    @GetMapping("/json")
    public JSONObject getData() {
        JSONObject data = new JSONObject(8);
        data.put("name", "李白");
        data.put("age", 18);
        data.put("sex", "male");
        return data;
    }

    /**
     * 测试全局异常处理
     *
     * @return
     */
    @ApiOperation(value = "测试全局异常处理")
    @GetMapping("/error")
    public JSONObject error() {
        throw new ResultException("内部服务器异常,请联系管理员", ResultStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/debug")
    public void debug() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + i);
        }
    }
}
