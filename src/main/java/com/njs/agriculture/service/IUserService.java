package com.njs.agriculture.service;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.pojo.UserRelationship;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
public interface IUserService {
    ServerResponse apply(User user);

    ServerResponse login(String phonenum, String password);

    ServerResponse upload(MultipartFile file);

    ServerResponse passwordChange(String phoneNum, String oldPassword, String newPassword);

    ServerResponse updateInfo(String key, String value, int userId);

    ServerResponse getUsers(int pageNum, int pageSize);

    ServerResponse<UserRelationship> isManager(int id);

    ServerResponse positionUpdate(int id, int position);

}
