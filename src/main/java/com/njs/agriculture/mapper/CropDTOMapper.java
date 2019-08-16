package com.njs.agriculture.mapper;

import com.njs.agriculture.dto.CropDTO;

import java.util.List;

public interface CropDTOMapper {
    List<CropDTO> selectAll();
}