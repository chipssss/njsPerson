package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Converter;
import com.njs.agriculture.bo.MachineBO;
import com.njs.agriculture.pojo.Machining;

import com.njs.agriculture.utils.ConvertUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/10
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MachineVO extends MachineBO {

    private List<String> images;

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
            machineVO.images = ConvertUtil.string2List(machining.getImageList());
            return machineVO;
        }
    }
}
