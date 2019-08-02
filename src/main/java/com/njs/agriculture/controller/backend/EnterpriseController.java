package com.njs.agriculture.controller.backend;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Enterprise;
import com.njs.agriculture.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@RestController
@RequestMapping("/backend/enterprise/")
public class EnterpriseController {

    @Autowired
    private IEnterpriseService iEnterpriseService;

    @RequestMapping("enterpriseAdd.do")
    public ServerResponse enterpriesAdd(@RequestBody Enterprise enterprise){
        return iEnterpriseService.enterpriseAdd(enterprise);
    }

    @GetMapping("enterpriseGet.do")
    public ServerResponse enterpriseGet(int status){
        return iEnterpriseService.enterpriseGet(status);
    }

    @GetMapping("personnelGet.do")
    public ServerResponse personnelGet(int enterpriseId){
        return iEnterpriseService.personnelGet(enterpriseId);
    }
}
