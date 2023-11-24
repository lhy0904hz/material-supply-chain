package com.material.chain.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PurchaseOrderVo {

    @ApiModelProperty(value = "采购单id")
    private Long purchaseId;

    @ApiModelProperty(value = "采购类型 国内：internal 国际：global")
    private String purchaseType;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "币种")
    private String currency;

    @ApiModelProperty(value = "国家编码")
    private String countryCode;

    @ApiModelProperty(value = "采购单编号")
    private String orderNo;

    @ApiModelProperty(value = "采购单物料集合")
    private List<PurchaseOrderItemVo> itemList;

    @ApiModelProperty(value = "地址信息")
    private PurchaseOrderAddressVo address;
}
