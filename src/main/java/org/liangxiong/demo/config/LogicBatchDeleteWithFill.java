package org.liangxiong.demo.config;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-20 10:13
 * @description 批量逻辑删除的时候填充字段
 **/
public class LogicBatchDeleteWithFill extends AbstractMethod {

    /**
     * mapper 对应的方法名
     */
    private static final String MAPPER_METHOD = "batchDeleteWithFill";

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE;
        if (tableInfo.isWithLogicDelete()) {
            List<TableFieldInfo> fieldInfos = tableInfo.getFieldList().stream()
                    .filter(TableFieldInfo::isWithUpdateFill)
                    .collect(toList());
            if (CollectionUtils.isNotEmpty(fieldInfos)) {
                String sqlSet = "SET " + fieldInfos.stream().map(i -> i.getSqlSet(ENTITY_DOT)).collect(joining(EMPTY))
                        + tableInfo.getLogicDeleteSql(false, true);
                sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet,
                        sqlWhereEntityWrapper(true, tableInfo));
            } else {
                sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlLogicSet(tableInfo),
                        sqlWhereEntityWrapper(true, tableInfo));
            }
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return this.addUpdateMappedStatement(mapperClass, modelClass, MAPPER_METHOD, sqlSource);
        } else {
            sqlMethod = SqlMethod.DELETE;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlWhereEntityWrapper(true, tableInfo));
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return this.addDeleteMappedStatement(mapperClass, MAPPER_METHOD, sqlSource);
        }
    }

    @Override
    public String getMethod(SqlMethod sqlMethod) {
        // 自定义 mapper 方法名
        return MAPPER_METHOD;
    }
}
