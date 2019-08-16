package com.njs.agriculture.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/16
 * @Description:
 */
@Data
public class CropDTO {
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

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int cropId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cropName;

    public CropDTO(Integer firstcateId, String firstcateName, Integer secondcateId, String secondcateName, Integer thirdcateId, String thirdcateName, Integer cropId, String cropName) {
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
        if(cropId == null || cropName == null){
            this.cropId = 0;
            this.cropName = null;
        }else {
            this.cropId = cropId;
            this.cropName = cropName;
        }
    }


}
