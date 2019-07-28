package com.njs.agriculture.VO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
@Data
public class ProcessRecordVO {
    private Integer id;

    private Integer batchId;

    private String location;

    private Integer cropId;

    private String operation;

    private String inputRecord;

    private Date createTime;

    private String remark;

    private String weather;

    private List<String> images;
}
