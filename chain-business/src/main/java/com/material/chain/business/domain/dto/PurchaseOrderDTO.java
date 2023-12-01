package com.material.chain.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {

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

    @ApiModelProperty(value = "采购单物料集合")
    private List<PurchaseOrderItemDTO> purchaseOrderItemList;

    @ApiModelProperty(value = "收件人地址")
    private PurchaseOrderAddressDTO address;

}
