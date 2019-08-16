package com.njs.agriculture.mapper;

import com.njs.agriculture.dto.InputDTO;

import java.util.List;

public interface InputDTOMapper {
    int insert(InputDTO record);

    int insertSelective(InputDTO record);

    List<InputDTO> selectAll();
}