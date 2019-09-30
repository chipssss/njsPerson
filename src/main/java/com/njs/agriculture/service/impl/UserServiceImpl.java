package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.VO.EnterpriseInfoVO;
import com.njs.agriculture.VO.RelationshipVO;
import com.njs.agriculture.VO.UserInfoVO;
import com.njs.agriculture.VO.UserVO;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.common.TokenCache;
import com.njs.agriculture.mapper.EnterpriseMapper;
import com.njs.agriculture.mapper.UserMapper;
import com.njs.agriculture.mapper.UserRelationshipMapper;
import com.njs.agriculture.pojo.Enterprise;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.pojo.UserRelationship;
import com.njs.agriculture.service.IFileService;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.MD5Util;
import com.njs.agriculture.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.beans.Beans;
import java.util.*;
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

    @Autowired
    IFileService iFileService;

    @Autowired
    UserRelationshipMapper userRelationshipMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Override
    public ServerResponse checkNum(String num) {
        String number = num.trim();
        if(!isMobile(number)){
            return ServerResponse.createByErrorMessage("电话号码格式错误！");
        }
        int resultRow = userMapper.countByPhoneNum(number);
        return ServerResponse.createByResultRow(resultRow);
    }

    @Override
    public ServerResponse apply(User user) {
        if (!isMobile(user.getPhonenum())) {
            return ServerResponse.createByErrorMessage("电话号码格式错误！");
        }
        ServerResponse response = checkValid(user.getPhonenum(), Const.UserInfo.PHONENUM);
        if (!response.isSuccess()) {
            return response;
        }
        ServerResponse response1 = checkValid(user.getUsername(), Const.UserInfo.USERNAME);
        if (!response.isSuccess()) {
            return response;
        }
        user.setType(Const.Role.ROLE_CONSUMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("注册成功！", user);
    }

    @Override
    public ServerResponse<User> login(String phonenum, String password) {
        if (!isMobile(phonenum)) {
            return ServerResponse.createByErrorMessage("电话号码格式错误！");
        }
        ServerResponse serverResponse = checkValid(phonenum, Const.UserInfo.PHONENUM);
        if (serverResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage("手机号码不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(phonenum, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        List<UserRelationship> userRelationshipList = userRelationshipMapper.selectByUserId(user.getUserId());
        List<EnterpriseInfoVO> enterpriseInfoVOList = Lists.newLinkedList();
        for (UserRelationship userRelationship : userRelationshipList) {
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userRelationship.getEnterpriseId());
            if(enterprise != null){
                EnterpriseInfoVO enterpriseInfoVO = new EnterpriseInfoVO();
                enterpriseInfoVO.setEnterpriseId(enterprise.getId());
                enterpriseInfoVO.setEnterpriseName(enterprise.getName());
                enterpriseInfoVO.setRole(getPosition(userRelationship.getPosition()));
                enterpriseInfoVOList.add(enterpriseInfoVO);
            }
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        userInfoVO.setEnterpriseInfoVOList(enterpriseInfoVOList);
        /*String token = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKEN_PREFIX + user.getUserId(), token);
        Map map = Maps.newHashMap();
        map.put("user", user);
        map.put("token", token);*/
        return ServerResponse.createBySuccess("登录成功", userInfoVO);

    }

    @Override
    public ServerResponse upload(MultipartFile file) {
        String path = PropertiesUtil.getProperty("uploadDir") + "user";
        String fileName = iFileService.upload(file, path);
        Map map = new HashMap();
        map.put("uri", Const.USERIMGPREFIX + fileName);
        map.put("url", PropertiesUtil.getProperty("server") + Const.USERIMGPREFIX + fileName);
        return ServerResponse.createBySuccess(map);
    }

    @Override
    public ServerResponse passwordChange(String phoneNum, String oldPassword, String newPassword) {
        if(oldPassword == null || StringUtils.isBlank(oldPassword)){
            String md5PasswordNew = MD5Util.MD5EncodeUtf8(newPassword);
            int resultRow = userMapper.updatePasswordByPhoneNum(phoneNum, md5PasswordNew);
            return ServerResponse.createByResultRow(resultRow);
        }
        if (oldPassword.equals(newPassword)) {
            return ServerResponse.createByErrorMessage("两次密码相同");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(oldPassword);
        User user = userMapper.selectLogin(phoneNum, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        String md5PasswordNew = MD5Util.MD5EncodeUtf8(newPassword);
        user.setPassword(md5PasswordNew);
        int resultRow = userMapper.updateByPrimaryKeySelective(user);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("修改密码失败,请联系管理员");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse updateInfo(String key, String value, int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("id错误！");
        }
        if (key.equals(Const.UserInfo.IMAGE)) {
            user.setImage(value);
        } else if (Const.UserInfo.USERNAME.equals(key)) {
            ServerResponse serverResponse = checkValid(key, Const.UserInfo.USERNAME);
            if (!serverResponse.isSuccess()) {
                return serverResponse;
            }
            user.setUsername(value);
        } else if (Const.UserInfo.TYPE.equals(key)) {
            user.setType(new Integer(key));
        } else if (Const.UserInfo.PHONENUM.equals(key)) {
            user.setPhonenum(value);
        }
        int resultRow = userMapper.updateByPrimaryKey(user);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("更新失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse getUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAll();
        List<UserVO> userVOS = Lists.newLinkedList();
        for (User user : users) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            List<RelationshipVO> relationships = Lists.newLinkedList();
            UserRelationship userRelationship1 = userRelationshipMapper.selectAdminByUserId(user.getUserId());
            if (userRelationship1 != null) {
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userRelationship1.getEnterpriseId());
                RelationshipVO relationship = new RelationshipVO(enterprise.getName(), getPosition(userRelationship1.getPosition()));
                relationships.add(relationship);
            } else {
                List<UserRelationship> userRelationships = userRelationshipMapper.selectByUserId(user.getUserId());
                for (UserRelationship userRelationship : userRelationships) {
                    Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userRelationship.getEnterpriseId());
                    RelationshipVO relationship = new RelationshipVO(enterprise.getName(), getPosition(userRelationship.getPosition()));
                    relationships.add(relationship);
                }
            }
            userVO.setRelationships(relationships);
            userVOS.add(userVO);
        }
        PageInfo pageResult = new PageInfo(users);
        pageResult.setList(userVOS);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<Map> isManager(int userId) {
        UserRelationship userRelationship = userRelationshipMapper.selectAdminByUserId(userId);
        Map map = Maps.newHashMap();
        if (userRelationship == null) {
            map.put("source",0);
            map.put("sourceId", userId);
        }else{
            map.put("source",1);
            map.put("sourceId", userRelationship.getEnterpriseId());
        }
        return ServerResponse.createBySuccess(map);
    }

    @Override
    public ServerResponse positionUpdate(int id, int position) {
        UserRelationship userRelationship = new UserRelationship();
        userRelationship.setId(id);
        userRelationship.setPosition(position);
        int resultRow = userRelationshipMapper.updateByPrimaryKeySelective(userRelationship);
        if(resultRow == 0 ){
            return ServerResponse.createByErrorMessage("更新职位失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse userDel(int userId) {
        int resultRow = userMapper.deleteByPrimaryKey(userId);
        return ServerResponse.createByResultRow(resultRow);
    }

    public ServerResponse<String> checkValid(String str, String type) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.UserInfo.PHONENUM.equals(type)) {
                int resultCount = userMapper.checkPhonenum(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("手机号码已存在");
                }
            } else if (Const.UserInfo.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUserName(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public String getPosition(int i) {
        if (i == 0) return "员工";
        else if (i == 1) return "经理";
        else if (i == 2) return "负责人";
        else return "员工";
    }

}
