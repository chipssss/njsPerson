package com.njs.agriculture.mapper;

import com.njs.agriculture.dto.ProductionDTO;

import java.util.List;

public interface ProductDTOMapper {
    List<ProductionDTO> selectAll();
}