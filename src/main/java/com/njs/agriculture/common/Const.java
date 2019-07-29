package com.njs.agriculture.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/12
 * @Description:
 */
public class Const {
    public static final String CURRENT_ADMIN = "currentAdmin";

    public static final String CURRENT_USER = "currentUser";

    public static final String USERIMGPREFIX = "img/user/";

    public interface InputRole{
        int USER = 0;
        int ENTERPRISE = 1;
    }

    public interface UserInfo{
        String USERNAME = "username";
        String TYPE = "type";
        String IMAGE = "image";
        String PHONENUM = "phonenum";
    }

    public interface Role{
        int ROLE_MANAGE = 0; //管理部门
        int ROLE_CONSUMER = 1;//消费者
        int ROLE_FARMER = 2;//农户
        int ROLE_ENTERPRISE = 3; // 企业
        int ROLE_SCIENCE = 4; //科研机构
        int ROLE_INDUSTRY = 5; //行业协会
        int ROLE_OTHER = 6; //其他产业链
    }

    public interface InputListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
        Set<String> TIME_ASC_DESC = Sets.newHashSet("create_time_desc","create_time_asc");
        Set<String> QUANTITY_ASC_DESC = Sets.newHashSet("quantity_desc","quantity_asc");

    }
}
