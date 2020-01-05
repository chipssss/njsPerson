package com.njs.agriculture.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backend/news")
public class NewsController {
    @Autowired
    INewsService iNewsService;
    @PostMapping("add.do")
    public ServerResponse addNews(@RequestBody JSONObject jsonObject){

        return iNewsService.add(jsonObject);
    }
    @PostMapping("delete.do")
    public ServerResponse deleteNews(@RequestBody JSONObject jsonObject){
        return iNewsService.delete(jsonObject.getInteger("id"));

    }
    @GetMapping("getAllNews.do")
    public ServerResponse getAll(){
        return iNewsService.getAll();
    }
    @PostMapping("updateIntrodution.do")
    public ServerResponse updateIntrodution(@RequestBody JSONObject jsonObject){
        return iNewsService.updateIntrodution(jsonObject);
    }
    @GetMapping("getIntrodution.do")
    public ServerResponse getIntrodution(){

        return iNewsService.getIntrodution();
    }
}
