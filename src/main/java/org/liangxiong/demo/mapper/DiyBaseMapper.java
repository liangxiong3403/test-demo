package org.liangxiong.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.liangxiong.demo.entity.BaseEntity;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-27 08:05
 * @description
 **/
public interface DiyBaseMapper<T extends BaseEntity> extends BaseMapper<T> {

    Integer deleteByIdWithFill(T t);

    Integer batchDeleteWithFill(T t);

    /**
     * 删除操作,自动填充修改人和修改时间
     *
     * @param queryWrapper
     * @param updatedBy
     * @param updatedTime
     * @return
     */
    int delete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper, @Param("updatedBy") String updatedBy, @Param("updatedTime") String updatedTime);
}
