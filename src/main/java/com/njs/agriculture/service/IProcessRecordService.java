package com.njs.agriculture.service;


import com.njs.agriculture.VO.ProcessRecordInfoVO;
import com.njs.agriculture.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
public interface IProcessRecordService {



    /**
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @param fieldId
     * @param cropId
     * @param pageNum 默认为1
     * @param pageSize 默认为20
     * @return
     */
    ServerResponse processRecord(int userId, String startTime, String endTime, int fieldId, int cropId, int pageNum, int pageSize);

    ServerResponse getRecordCrop(int userId);

    /**
     * 溯源，通过二维码查询对应的记录
     * @param batchId
     * @return
     */
    ServerResponse trace(int pageNum, int pageSize, Date startTime, Date endTime, int batchId);

    /**
     * 生产溯源二维码的id
     * @param recordIds
     * @return
     */
    ServerResponse generateTrace(int batchId, List<Integer> recordIds);

    /**
     * 添加生产记录
     * @param processRecordInfoVO
     * @return
     */
    ServerResponse addProcess(ProcessRecordInfoVO processRecordInfoVO, String userName);

    /**
     * 生产记录图片上传
     * @param files
     * @return
     */
    ServerResponse processImgUpload(List<MultipartFile> files);

    ServerResponse getOperation();

    ServerResponse getFieldsByBatchExist(int userId);

    ServerResponse getBatchesByUserId(int userId);

    ServerResponse getRecoveryRecord(int source, int sourceId);


}
