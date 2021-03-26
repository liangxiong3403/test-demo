package org.liangxiong.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.liangxiong.demo.entity.User;
import org.liangxiong.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-19 21:37
 * @description
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/list")
    public List<User> list() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lt("ID", 5);
        return userMapper.selectList(wrapper);
    }

    @PostMapping("add")
    public Boolean save(@RequestBody User user) {
        return userMapper.insert(user) == 1;
    }

    @GetMapping("/getOne")
    public User getOne(@RequestParam Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("ID", id);
        return userMapper.selectOne(wrapper);
    }

    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("ID", id);
        return userMapper.deleteByIdWithFill(User.builder().id(id).build()) == 1;
    }

    @DeleteMapping("/batchDelete")
    public Boolean batchDelete(@RequestBody List<Long> ids) {
        return userMapper.batchDeleteWithFill(User.builder().ids(ids).build()) > 0;
    }

    @PutMapping("/update")
    public Boolean delete(@RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("ID", user.getId());
        return userMapper.update(user, wrapper) == 1;
    }

    @GetMapping("/exportByEasyPoi")
    public void exportByEasyPoi(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户数据表格", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
        List<User> data = list();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),User.class,data);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    @GetMapping("/exportByEasyExcel")
    public void exportByEasyExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户数据表格", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");
        List<User> data = list();
        ServletOutputStream outputStream = response.getOutputStream();
        EasyExcel.write(outputStream, User.class).sheet("模板").doWrite(data);
    }
}
