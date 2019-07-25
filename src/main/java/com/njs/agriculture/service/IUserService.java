package com.njs.agriculture.service;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.User;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
public interface IUserService {
    ServerResponse apply(User user);

    ServerResponse login(String phonenum, String password);

}
