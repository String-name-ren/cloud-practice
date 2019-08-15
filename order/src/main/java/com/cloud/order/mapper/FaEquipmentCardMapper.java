package com.cloud.order.mapper;

import com.cloud.order.model.FaEquipmentCard;
import org.mapstruct.Mapper;

@Mapper
public interface FaEquipmentCardMapper {
    int deleteByPrimaryKey(Long eqCardId);

    int insert(FaEquipmentCard record);

    int insertSelective(FaEquipmentCard record);

    FaEquipmentCard selectByPrimaryKey(Long eqCardId);

    int updateByPrimaryKeySelective(FaEquipmentCard record);

    int updateByPrimaryKey(FaEquipmentCard record);
}