package com.njs.agriculture.controller.backend;



import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/12
 * @Description:
 */
@RestController
@RequestMapping("/backend/user")
public class AdminController {

    @Autowired
    private IUserService iUserSerivce;






}
