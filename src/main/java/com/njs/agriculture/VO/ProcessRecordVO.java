package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String batchName;

    private String fieldName;

    private String location;

    private Integer cropId;

    private String cropName;

    private String operation;

    private String inputRecord;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String remark;

    private String weather;

    private List<String> images;
}
