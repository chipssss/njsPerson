package com.njs.agriculture.VO;

import lombok.Data;

import java.util.Objects;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/31
 * @Description:
 */
@Data
public class FieldListVO {

    private int id;

    private String name;

    public FieldListVO() {
    }

    public FieldListVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldListVO that = (FieldListVO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
