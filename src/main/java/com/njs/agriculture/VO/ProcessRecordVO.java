package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.pojo.InputStream;
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

    private String fieldName;

    private String location;

    private Integer cropId;

    private String cropName;

    private String operation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String inputRecord;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<InputStream> inputStreamList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String remark;

    private String weather;

    private List<String> images;

    /**
     * 拼接成完整路径
     */
    public void convertImageUrl() {
        if (images == null) {
            return;
        }
        for (int i = 0; i < images.size(); i++) {
            images.set(i, Const.SERVER_URL + images.get(i));
        }
    }
}
