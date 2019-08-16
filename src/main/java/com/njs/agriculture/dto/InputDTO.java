package com.njs.agriculture.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/16
 * @Description:
 */
@Data
public class InputDTO {
    private int firstcateId;

    private String firstcateName;

    @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
    private int secondcateId;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String secondcateName;

    public InputDTO(Integer firstcateId, String firstcateName, Integer secondcateId, String secondcateName) {
        this.firstcateId = firstcateId;
        this.firstcateName = firstcateName;
        if (secondcateId == null || secondcateName == null) {
            this.secondcateId = 0;
            this.secondcateName = null;
        }else {
            this.secondcateId = secondcateId;
            this.secondcateName = secondcateName;
        }
    }


}
