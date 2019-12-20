package com.njs.agriculture.controller.backend;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.AppErrorDO;
import com.njs.agriculture.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chips
 * @date: 2019-12-20
 * @description:
 **/
@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    IAppService iAppService;

    @RequestMapping("uploadError.do")
    public ServerResponse uploadError(@RequestBody AppErrorDO errorDO) {
        return iAppService.saveError(errorDO);
    }
}
