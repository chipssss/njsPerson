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
        String name; // 使用投入品的id
        double quantity; // 使用数量
        int id;//来源 个人还是企业

        public Input(){
        }

        public Input(String name, double quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String  getName() {
            return name;
        }

        public void setName(String name) {
            this.name= name;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public int getId () {
            return id;
        }
    }



}
