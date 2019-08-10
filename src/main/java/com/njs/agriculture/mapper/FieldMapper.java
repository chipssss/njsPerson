package com.njs.agriculture.mapper;

import com.njs.agriculture.VO.FieldListVO;
import com.njs.agriculture.pojo.Field;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Field record);

    int insertSelective(Field record);

    Field selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);

    List<Field> selectBySourceId(@Param("source") int source, @Param("sourceId") int sourceId);

    List<FieldListVO> selectAllBySourceId(@Param("source") int source, @Param("sourceId") int sourceId);

    List<FieldListVO> selectExistedByUserId(@Param("source") int source, @Param("sourceId") int sourceId);
}