package com.material.chain.logistics.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsOrderAddressDTO {

    @ApiModelProperty(value = "收件人信息")
    private String recipientProvince;

    /**
     * 收件人市
     */
    @ApiModelProperty(value = "收件人市")
    private String recipientCity;

    /**
     * 收件人区
     */
    @ApiModelProperty(value = "收件人区")
    private String recipientArea;

    /**
     * 收件人姓名
     */
    @ApiModelProperty(value = "收件人姓名")
    private String recipientName;

    /**
     * 收件人电话
     */
    @ApiModelProperty(value = "收件人电话")
    private String recipientPhone;

    /**
     * 收件人详细地址
     */
    @ApiModelProperty(value = "收件人详细地址")
    private String recipientAddress;

    /**
     * 寄件人省
     */
    @ApiModelProperty(value = "寄件人省")
    private String senderProvince;

    /**
     * 寄件人市
     */
    @ApiModelProperty(value = "寄件人市")
    private String senderCity;

    /**
     * 寄件人区
     */
    @ApiModelProperty(value = "寄件人区")
    private String senderArea;

    /**
     * 寄件人姓名
     */
    @ApiModelProperty(value = "寄件人姓名")
    private String senderName;

    /**
     * 寄件人电话
     */
    @ApiModelProperty(value = "寄件人电话")
    private String senderPhone;

    /**
     * 寄件人详细地址
     */
    @ApiModelProperty(value = "寄件人详细地址")
    private String senderAddress;
}
