package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Converter;
import com.njs.agriculture.bo.MachineBO;
import com.njs.agriculture.pojo.Machining;

import com.njs.agriculture.utils.ConvertUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/10
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MachineVO extends MachineBO {

    public MachineVO() {
    }

    public MachineVO(Integer id, Integer stockId, Integer quantity, Integer output, String level, String record, String inspector, String imageList, Date createTime, Integer source, Integer sourceId, Integer typeStatus, Integer fieldId, String batchId) {
        super(id, stockId, quantity, output, level, record, inspector, imageList, createTime, source, sourceId, typeStatus, fieldId, batchId);
    }

    private List<String> images;
    private String fieldName;
    private String status;

    public String getFieldName() {
        return fieldName;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Machining converTOMachining(){
        MachineVOConvert machineVOConvert = new MachineVOConvert();
        Machining convert = machineVOConvert.convert(this);
        return convert;
    }

    public static MachineVO convertFor(Machining machining){
        MachineVOConvert machineVOConvert = new MachineVOConvert();
        MachineVO convet = machineVOConvert.reverse().convert(machining);
        return convet;
    }

    public void setFieldName(String name) {
        fieldName = name;
    }

    private static class MachineVOConvert extends Converter<MachineVO, Machining> {
        @Override
        protected Machining doForward(MachineVO machineVO) {
            Machining machining = new Machining();
            BeanUtils.copyProperties(machineVO,machining);
            if(machineVO.images != null){
                machining.setImageList(ConvertUtil.list2String(machineVO.images));
            }
            return machining;
        }

        @Override
        protected MachineVO doBackward(Machining machining) {
            MachineVO machineVO = new MachineVO();
            BeanUtils.copyProperties(machining,machineVO);
            machineVO.setStatus();
            machineVO.images = ConvertUtil.string2List(machining.getImageList());
            return machineVO;
        }
    }

    private void setStatus() {
        this.status = getTypeStatus()==0? "加工":"包装";
    }
}
