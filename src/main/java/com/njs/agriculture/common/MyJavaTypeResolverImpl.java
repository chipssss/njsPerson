package com.njs.agriculture.common;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description: mybatis生成代码类型自定义转换
 */
public class MyJavaTypeResolverImpl extends JavaTypeResolverDefaultImpl {
    public MyJavaTypeResolverImpl() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
