package com.cloud.order.service;

import com.cloud.order.mapper.FaEquipmentCardMapper;
import com.cloud.order.model.FaEquipmentCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-19 13:45
 **/
@Service
public class FaEquipmentCardService {

    @Autowired
    private FaEquipmentCardMapper cardMapper;

    public FaEquipmentCard getCard(){
        return cardMapper.selectByPrimaryKey(7857L);
    }

}
