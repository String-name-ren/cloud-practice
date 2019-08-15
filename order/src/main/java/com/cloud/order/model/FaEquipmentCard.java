package com.cloud.order.model;

import lombok.Data;

import java.util.Date;
@Data
public class FaEquipmentCard {
    private Long eqCardId;

    private String eqCardCode;

    private Long eqId;

    private String eqName;

    private Long branchId;

    private Long shopId;

    private Date gmtCreated;

    private Date gmtModified;

    private Byte eqStatus;

    private String eqStatusCn;

    private Long eqBatchId;

    private String branchName;

    private String shopName;

}