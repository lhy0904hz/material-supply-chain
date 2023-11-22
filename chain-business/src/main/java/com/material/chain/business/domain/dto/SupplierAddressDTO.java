package com.material.chain.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class SupplierAddressDTO {

    @ApiModelProperty(value = "地址id")
    private Long addressId;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "邮编")
    private String zipCode;

    @ApiModelProperty(value = "寄件人名称")
    private String senderName;

    @ApiModelProperty(value = "寄件人手机号")
    private String senderPhone;

    @ApiModelProperty(value = "是否为默认地址 0：默认 1：非默认")
    private Integer isDefault;


}
