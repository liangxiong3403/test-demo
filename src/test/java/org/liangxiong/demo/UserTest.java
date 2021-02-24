package org.liangxiong.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.liangxiong.demo.entity.User;
import org.liangxiong.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2020-09-06 14:01
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListUsers() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(User::getId, 3, 5);
        List<User> userList = userMapper.selectList(wrapper);
        Assertions.assertEquals(3, userList.size());
        String username = "Tom";
        User tom = userMapper.selectByMap(Collections.singletonMap("name", username)).get(0);
        Assertions.assertEquals(username, tom.getName());
        Integer count = userMapper.selectCount(null);
        Assertions.assertEquals(5, count);
    }

    @Test
    public void testConcatString() {
        StringJoiner joiner = new StringJoiner(",");
        Stream.of("1", "2", "3", "5", "6").forEach(joiner::add);
        System.out.println(joiner.toString());
    }
}
