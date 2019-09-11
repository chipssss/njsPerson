package com.njs.agriculture.VO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/10
 * @Description:
 */
@Data
public class StreamVO {
    private String productName;

    private String operation;

    private int productId;

    private String batchId;

    private int quantity;

    private Date createTime;
}
