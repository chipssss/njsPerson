package com.njs.agriculture.service.impl;

import com.google.common.collect.Maps;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.common.TokenCache;
import com.njs.agriculture.mapper.UserMapper;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse apply(User user) {
        if(!isMobile(user.getPhonenum())){
            return ServerResponse.createByErrorMessage("电话号码格式错误！");
        }
        ServerResponse response = checkValid(user.getPhonenum(), Const.PHONENUM);
        if(!response.isSuccess()){
            return response;
        }
        user.setType(Const.Role.ROLE_CONSUMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<Map> login(String phonenum, String password) {
        if(!isMobile(phonenum)){
            return ServerResponse.createByErrorMessage("电话号码格式错误！");
        }
        int resultCount = userMapper.checkPhonenum(phonenum);
        if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("电话号码不存在！");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user  = userMapper.selectLogin(phonenum, md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        String token = UUID.randomUUID().toString();
        // TODO 加检测，重复登录则删除上一个
        TokenCache.setKey(TokenCache.TOKEN_PREFIX + user.getUserId(), token);
        Map map = Maps.newHashMap();
        map.put("user", user);
        map.put("token", token);
        return ServerResponse.createBySuccess("登录成功",map);

    }


    public ServerResponse<String> checkValid(String str,String type){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.PHONENUM.equals(type)){
                int resultCount = userMapper.checkPhonenum(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

     /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
      * */


    public static boolean isMobile(String mobile) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

}
