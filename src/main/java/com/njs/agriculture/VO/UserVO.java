package com.njs.agriculture.VO;


import com.njs.agriculture.pojo.User;
import lombok.Data;

import java.util.List;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
@Data
public class UserVO extends User {

    private List<RelationshipVO> relationships;



}
