package com.njs.agriculture.VO;

import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
@Data
public class RelationshipVO {
    private String enterpriseName;
    private String position;

    public RelationshipVO(String enterpriseName, String position) {
        this.enterpriseName = enterpriseName;
        this.position = position;
    }
}
