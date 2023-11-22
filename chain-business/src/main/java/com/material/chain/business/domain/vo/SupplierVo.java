package com.material.chain.business.domain.vo;

import com.material.chain.business.domain.dto.SupplierAddressDTO;
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
public class SupplierVo {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "主体名称")
    private String subjectName;

    @ApiModelProperty(value = "供应商等级 S A B C D")
    private String level;

    @ApiModelProperty(value = "供应商类型 1：品牌供应商 2：自营供应商")
    private String supplierTypeDesc;

    @ApiModelProperty(value = "供应商地址")
    private List<SupplierAddressVo> addressList;
}
