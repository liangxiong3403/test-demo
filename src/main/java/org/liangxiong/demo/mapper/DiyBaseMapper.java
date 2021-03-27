package org.liangxiong.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
