package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.User;
import lombok.Data;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/17
 * @Description:
 */
@Data
public class UserInfoVO extends User {
    List<EnterpriseInfoVO> enterpriseInfoVOList;
}
