package com.njs.agriculture.common;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/12
 * @Description:
 */
public class Const {
    public static final String CURRENT_ADMIN = "currentAdmin";

    public static final String CURRENT_USER = "currentUser";

    public static final String PHONENUM = "phonenum";

    public interface Role{
        int ROLE_MANAGE = 0; //管理部门
        int ROLE_CONSUMER = 1;//消费者
        int ROLE_FARMER = 2;//农户
        int ROLE_ENTERPRISE = 3; // 企业
        int ROLE_SCIENCE = 4; //科研机构
        int ROLE_INDUSTRY = 5; //行业协会
        int ROLE_OTHER = 6; //其他产业链
    }
}
