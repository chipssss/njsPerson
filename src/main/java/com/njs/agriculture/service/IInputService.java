package com.njs.agriculture.service;

import com.njs.agriculture.VO.InputCategoryVO;
import com.njs.agriculture.VO.InputVO;
import com.njs.agriculture.common.ServerResponse;

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
    ServerResponse<List<InputCategoryVO>> categoryInfo();

    /**
     * 农资获取
     * @param firstCateId 默认“所有”，即为0或不需要填写
     * @param secondCateId 默认“所有“，即为0或不需要填写，如果有二级类别id则不读取一级id
     * @param order 默认“降序”，默认不需要填写,desc或者asc升序
     * @param flag 价格price，时间time，数量quantity 默认为时间
     * @param sourceId
     * @param source 来源，0为用户，1为企业
     * @return
     */
    ServerResponse infoGet(int firstCateId, int secondCateId, String order, String flag, int sourceId, int source);

    /**
     * 库存提醒
     * @param sourceId 来源id
     * @param source 来源 0为用户 1为企业
     * @param type 0为数量、1为保质期
     * @param threshold 阈值
     * @return
     */
    ServerResponse StockRemind(int sourceId, int source, int type, int threshold);
}
