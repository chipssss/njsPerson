package com.njs.agriculture.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/16
 * @Description:
 */
@Data
public class ProductionDTO {
    private int firstcateId;

    private String firstcateName;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int secondcateId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String secondcateName;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int thirdcateId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thirdcateName;

    public ProductionDTO(Integer firstcateId, String firstcateName, Integer secondcateId, String secondcateName, Integer thirdcateId, String thirdcateName) {
        this.firstcateId = firstcateId;
        this.firstcateName = firstcateName;
        if(secondcateId == null || secondcateName == null){
            this.secondcateId = 0;
            this.secondcateName = null;
        }else{
            this.secondcateId = secondcateId;
            this.secondcateName = secondcateName;
        }
        if(thirdcateId == null || thirdcateName == null){
            this.thirdcateId = 0;
            this.thirdcateName = null;
        }else {
            this.thirdcateId = thirdcateId;
            this.thirdcateName = thirdcateName;
        }


    }

}
