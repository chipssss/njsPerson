package com.njs.agriculture.service;


import com.njs.agriculture.VO.InputVO;
import com.njs.agriculture.VO.ProcessRecordInfoVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.InputConsume;

import java.util.List;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
public interface IInputService {

    /**
     * 农资购入
     * @param inputVO 农资传输对象
     * @return
     */
    ServerResponse purchase(InputVO inputVO);

    /**
     * 农资类别获取
     * @return
     */
    ServerResponse categoryInfo(int pageNum, int pageSize);

    /**
     * 农资获取安卓接口
     * @return
     */
    ServerResponse categoryInfoForAndroid();


    ServerResponse firstCateGet();

    ServerResponse secondCateGet();

    /**
     * 农资获取
     * @param firstCateId 默认“所有”，即为0或不需要填写
     * @param secondCateId 默认“所有“，即为0或不需要填写，如果有二级类别id则不读取一级id
     * @param orderBy
     * @param sourceId
     * @param source 来源，0为用户，1为企业
     * @return
     */
    ServerResponse infoGet(int firstCateId, int secondCateId, String orderBy, int sourceId, int source, int pageNum, int pageSize);

    /**
     * 农资汇总接口
     * @param source 0为用户 1 为企业
     * @param sourceId id
     * @return
     */
    ServerResponse sumGet(int source, int sourceId);
    /**
     * 库存提醒
     * @param sourceId 来源id
     * @param source 来源 0为用户 1为企业
     * @param type 0为数量、1为保质期
     * @param threshold 阈值
     * @return
     */
    ServerResponse stockRemind(int sourceId, int source, int type, int threshold);

    /**
     * 退回投入品
     * @param id
     * @param quantity
     * @return
     */
    ServerResponse returnInput(int id, float quantity);

    ServerResponse scanBarcode(String barCode);

    ServerResponse inputRecord(int source, int sourceId, int type);

    /**
     * 农资删除接口
     * @param id
     * @param flag 0/1/2代表农资 一类 二类
     * @param source 0为表示用户，1表示企业
     * @return
     */
    ServerResponse inputDel(int id, int flag, int source);

    /**
     * 添加农资类别
     * @param type   1 2 分别为一类二类
     * @param name
     * @param superiorId
     * @return
     */
    ServerResponse inputCateAdd(int type, String name, int superiorId);

    ServerResponse inputConsume(InputConsume inputConsume, int userId);

    ServerResponse inputConsumeList(int userId);

    ServerResponse inputConsumeReview(int id, int status, int userId);

    ServerResponse inputStreamAdd(int fieldId, int cropId, List<ProcessRecordInfoVO.Input> inputList, int userId, int recordId);

    ServerResponse inputStreamGet(int userId);
}
