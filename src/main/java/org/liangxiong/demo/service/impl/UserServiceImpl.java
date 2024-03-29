package org.liangxiong.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.liangxiong.demo.entity.User;
import org.liangxiong.demo.mapper.UserMapper;
import org.liangxiong.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-27 08:00
 * @description
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer deleteByIdWithFill(User user) {
        return userMapper.deleteByIdWithFill(user);
    }

    @Override
    public Integer batchDeleteWithFill(User user) {
        return userMapper.batchDeleteWithFill(user);
    }

    @Override
    public boolean remove(Wrapper<User> queryWrapper) {
        // 此处填充修改人和修改时间
        return SqlHelper.retBool(userMapper.delete(queryWrapper, null, null));
    }
}
