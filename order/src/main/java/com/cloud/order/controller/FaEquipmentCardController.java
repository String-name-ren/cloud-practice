package com.cloud.order.controller;

import com.cloud.order.model.FaEquipmentCard;
import com.cloud.order.service.FaEquipmentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-06-19 13:43
 **/
@RestController
public class FaEquipmentCardController {

    @Autowired
    private FaEquipmentCardService cardService;

    @GetMapping("getCard")
    public FaEquipmentCard getCard(){
        return cardService.getCard();
    }

}
