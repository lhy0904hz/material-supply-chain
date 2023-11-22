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
public class SupplierDTO {

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "每页总数")
    private Integer pageSize;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "主体名称")
    private String subjectName;

    @ApiModelProperty(value = "供应商等级 S A B C D")
    private String level;

    @ApiModelProperty(value = "供应商类型 1：品牌供应商 2：自营供应商")
    private Integer supplierType;

    @ApiModelProperty(value = "供应商地址")
    private SupplierAddressDTO addressDTO;
}
