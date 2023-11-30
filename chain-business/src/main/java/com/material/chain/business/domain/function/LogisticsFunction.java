package com.material.chain.business.domain.function;

import com.material.chain.business.domain.po.SupplierAddressPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * 物流所需要的数据实体泛型实体类
 * @param <K> 代表订单实体
 * @param <T> 代表订单地址实体
 * @param <R> 代表订单包裹实体
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsFunction<K, T, R> {

    @ApiModelProperty(value = "采购单主数据")
    private K purchaseOrder;

    @ApiModelProperty(value = "采购单收件人地址")
    private T purchaseAddress;

    @ApiModelProperty(value = "采购单包裹信息")
    private List<R> purchaseItemList;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "物流商id")
    private Long providerId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "寄件人地址")
    private SupplierAddressPo supplierAddress;

    /***************order**********************************/

    @ApiModelProperty(value = "业务系统单号")
    private Function<K, String> businessNoFunction;

    /***************address**********************************/

    @ApiModelProperty(value = "收件人信息")
    private Function<T, String> recipientProvinceFunction;

    @ApiModelProperty(value = "收件人市")
    private Function<T, String> recipientCityFunction;

    @ApiModelProperty(value = "收件人区")
    private Function<T, String> recipientAreaFunction;

    @ApiModelProperty(value = "收件人姓名")
    private Function<T, String> recipientNameFunction;

    @ApiModelProperty(value = "收件人电话")
    private Function<T, String> recipientPhoneFunction;

    @ApiModelProperty(value = "收件人详细地址")
    private Function<T, String> recipientAddressFunction;

    /***************item**********************************/

    @ApiModelProperty(value = "物料名称")
    private Function<R, String> materialNameFunction;

    @ApiModelProperty(value = "厚度（mm）")
    private Function<R, Integer> thicknessFunction;

    @ApiModelProperty(value = "宽度（mm）")
    private Function<R, Integer> widthFunction;

    @ApiModelProperty(value = "长度（mm）")
    private Function<R, Integer> lengthFunction;

    @ApiModelProperty(value = "重量（T）")
    private Function<R, BigDecimal> weightFunction;

    @ApiModelProperty(value = "单价")
    private Function<R, BigDecimal> unitPriceFunction;

    @ApiModelProperty(value = "数量")
    private Function<R, Integer> quantityFunction;

}
