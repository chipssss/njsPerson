package com.njs.agriculture.VO;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
@Data
public class ProcessRecordInfoVO {

    /**
     * 田块id
     */
    private int fieldId;

    private String location;

    /**
     * 操作列表
     */
    private List<String> operationList;

    /**
     * 农资列表
     */
    private List<Input> inputList;

    private String weather;

    private String remark;

    private List<String> images;

    public ProcessRecordInfoVO() {
    }

    public ProcessRecordInfoVO(int field, String location, List<String> operationList, List<Input> inputList, String weather, String remark) {
        this.fieldId = field;
        this.location = location;
        this.operationList = operationList;
        this.inputList = inputList;
        this.weather = weather;
        this.remark = remark;
    }

    public static class Input{
        private int inputId;
        private float quantity;
        private int source;

        public Input(){
        }

        public Input(int inputId, float quantity) {
            this.inputId = inputId;
            this.quantity = quantity;
        }

        public int getInputId() {
            return inputId;
        }

        public void setInputId(int inputId) {
            this.inputId = inputId;
        }

        public float getQuantity() {
            return quantity;
        }

        public void setQuantity(float quantity) {
            this.quantity = quantity;
        }

        public int getSource() {
            return source;
        }
    }



}
