package com.njs.agriculture.service;

import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.VO.ProcessRecordInfoVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Field;
import com.njs.agriculture.pojo.ProcessRecord;
import net.sf.jsqlparser.schema.Server;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
public interface IProcessRecordService {

    ServerResponse addField(FieldVO fieldVO);

    ServerResponse delField(int fieldId);

    ServerResponse modifyField(Field field);

    /**
     * 田块获取
     * @param userId
     * @return 普通用户返回个人田块，企业负责人返回企业所有田块
     */
    ServerResponse fieldInfo(int userId);

    /**
     * 获取田块批次
     * @param fieldId
     * @return
     */
    ServerResponse batchInfo(int fieldId);

    /**
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @param batchId
     * @param cropId
     * @param pageNum 默认为1
     * @param pageSize 默认为20
     * @return
     */
    ServerResponse processRecord(int userId, String startTime, String endTime, int batchId, int cropId, int pageNum, int pageSize);

    /**
     * 溯源，通过二维码查询对应的记录
     * @param qrcodeId
     * @return
     */
    ServerResponse trace(int qrcodeId);

    /**
     * 生产溯源二维码的id
     * @param recordIds
     * @return
     */
    ServerResponse generateTrace(List<Integer> recordIds);

    ServerResponse addProcess(ProcessRecordInfoVO processRecordInfoVO);

    ServerResponse processImgUpload(List<MultipartFile> files);

    ServerResponse returnInput(int id, float quantity);
}
