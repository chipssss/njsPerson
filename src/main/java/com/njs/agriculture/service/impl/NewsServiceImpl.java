package com.njs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.InputCategoryVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.dto.IntroductionDTO;
import com.njs.agriculture.mapper.IntroductionMapper;
import com.njs.agriculture.mapper.NewsMapper;
import com.njs.agriculture.pojo.Introduction;
import com.njs.agriculture.pojo.News;
import com.njs.agriculture.service.INewsService;
import com.njs.agriculture.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("iNewService")
public class NewsServiceImpl implements INewsService {
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    IntroductionMapper introductionMapper;
@Override
public ServerResponse add(JSONObject jsonObject){
    News news=new News();
    news.setAuthor(jsonObject.getString("author"));
    news.setTitle(jsonObject.getString("title"));
    news.setContent(jsonObject.getString("content"));
    news.setReporttime(DateUtil.strToDate(jsonObject.getString("reporttime"),DateUtil.STANDARD_FORMAT));
   newsMapper.insertSelective(news);
return ServerResponse.createBySuccessMessage("添加新闻成功");
}
@Override
    public ServerResponse delete(int id){
    newsMapper.deleteByPrimaryKey(id);
    return ServerResponse.createBySuccessMessage("删除新闻成功");
}
@Override
    public ServerResponse getAll(){
    List<News> newsList = Lists.newArrayList();
    newsList=newsMapper.selectAll();
    return ServerResponse.createBySuccess(newsList);
}
@Override
public ServerResponse updateIntrodution(JSONObject jsonObject){

    Introduction introduction=new Introduction();
    introduction.setContent(jsonObject.getString("content"));
    introduction.setAdId(jsonObject.getInteger("ad_id"));
    introductionMapper.update(introduction);
    return ServerResponse.createBySuccessMessage("修改成功");
}
@Override
    public   ServerResponse getIntrodution(){
    List<Introduction> introductionList=Lists.newArrayList();
    introductionList=introductionMapper.selectAll();
    List<IntroductionDTO> data=Lists.newArrayList();
    for(Introduction x:introductionList){
        IntroductionDTO introductionDTO=new IntroductionDTO();
        BeanUtils.copyProperties(x,introductionDTO);
        int num=introductionDTO.getAdId();
        if(num==0){
            introductionDTO.setName("区域优势");
        }
        else if(num==1){
            introductionDTO.setName("自然环境");
        }
        else if(num==2){

            introductionDTO.setName("生态优势");
        }
        else if(num==3){
            introductionDTO.setName("文化优势");

        }
        else if(num==4){
            introductionDTO.setName("科技优质");
        }
        else{
            return ServerResponse.createByErrorMessage("ad_id不存在");
        }
        data.add(introductionDTO);
    }
    return ServerResponse.createBySuccess(data);

}
}
