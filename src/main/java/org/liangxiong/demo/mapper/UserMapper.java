package org.liangxiong.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.liangxiong.demo.entity.User;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2020-09-06 13:55
 * @description
 **/
public interface UserMapper extends BaseMapper<User> {

    Integer deleteByIdWithFill(User user);

    Integer batchDeleteWithFill(@Param(Constants.ENTITY) User user, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
