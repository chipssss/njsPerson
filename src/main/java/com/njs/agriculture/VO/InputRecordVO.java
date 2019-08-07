package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/7
 * @Description:
 */
@Data
public class InputRecordVO {

    private int id;

    private String name;

    private float quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private String source;

    public InputRecordVO(int id, String name, float quantity, Date createTime, String source) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.createTime = createTime;
        this.source = source;
    }

    public InputRecordVO() {
    }
}
