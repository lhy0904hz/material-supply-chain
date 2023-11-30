package com.material.chain.business.service.impl;

import com.material.chain.base.exception.ApiException;
import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.business.domain.function.LogisticsFunction;
import com.material.chain.business.domain.vo.PurchaseOrderAddressVo;
import com.material.chain.business.domain.vo.PurchaseOrderItemVo;
import com.material.chain.business.domain.vo.SupplierAddressVo;
import com.material.chain.business.domain.vo.SupplierVo;
import com.material.chain.business.service.LogisticsService;
import com.material.chain.business.service.SupplierService;
import com.material.chain.logistics.domain.dto.LogisticsOrderAddressDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class LogisticsServiceImpl<K, T, R> implements LogisticsService<K, T, R> {

    @Autowired
    private SupplierService supplierService;


    @Override
    public LogisticsOrderDTO getLogisticsOrderParam(LogisticsFunction<K, T, R> function) {
        LogisticsOrderDTO logisticsOrderDTO = new LogisticsOrderDTO();

        K purchaseOrder = function.getPurchaseOrder();
        T purchaseAddress = function.getPurchaseAddress();
        List<R> purchaseItemList = function.getPurchaseItemList();

        //订单信息
        Function<K, String> businessNoFunction = function.getBusinessNoFunction();

        logisticsOrderDTO.setBusinessNo(businessNoFunction.apply(purchaseOrder));
        logisticsOrderDTO.setProviderId(function.getProviderId());
        logisticsOrderDTO.setUserId(function.getUserId());

        //包裹信息
        Function<R, String> itemNameFunction = function.getMaterialNameFunction();
        Function<R, Integer> quantityFunction = function.getQuantityFunction();
        Function<R, BigDecimal> unitPriceFunction = function.getUnitPriceFunction();
        Function<R, BigDecimal> weightFunction = function.getWeightFunction();

        if (CollectionUtils.isNotEmpty(purchaseItemList)) {
            LogisticsOrderItemDTO itemDTO = new LogisticsOrderItemDTO();
            itemDTO.setItemName(itemNameFunction.apply(purchaseItemList.get(0)));
            BigDecimal priceTotal = purchaseItemList.stream().map(item -> {
                Integer quantity = quantityFunction.apply(item);
                BigDecimal unitPrice = unitPriceFunction.apply(item);
                if (Objects.isNull(quantity) || Objects.isNull(unitPrice)) {
                    return BigDecimal.ZERO;
                }
                return unitPrice.multiply(new BigDecimal(quantity));
            }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            itemDTO.setPriceTotal(priceTotal);

            Integer quantityTotal = purchaseItemList.stream().mapToInt(quantityFunction::apply).sum();
            itemDTO.setQuantityTotal(quantityTotal);

            BigDecimal weightTotal = purchaseItemList.stream().map(weightFunction::apply).reduce(BigDecimal::add).orElse(null);
            itemDTO.setWeightTotal(weightTotal);
            logisticsOrderDTO.setOrderItem(itemDTO);
        }

        SupplierVo supplierInfo = supplierService.detail(function.getSupplierId());
        if (Objects.isNull(supplierInfo)) {
            throw new ApiException("供应商信息不存在");
        }

        SupplierAddressVo addressVo = Optional.of(supplierInfo)
                .map(SupplierVo::getAddressList)
                .orElseThrow(() -> new ApiException("供应商地址不存在"))
                .stream()
                .filter(address -> address.getIsDefault() == 0).findAny().orElse(null);
        if (Objects.isNull(addressVo)) {
            throw new ApiException("供应商地址不能为空");
        }

        //地址信息
        Function<T, String> recipientProvinceFunction = function.getRecipientProvinceFunction();
        Function<T, String> recipientCityFunction = function.getRecipientCityFunction();
        Function<T, String> recipientAreaFunction = function.getRecipientAreaFunction();
        Function<T, String> recipientAddressFunction = function.getRecipientAddressFunction();
        Function<T, String> recipientNameFunction = function.getRecipientNameFunction();
        Function<T, String> recipientPhoneFunction = function.getRecipientPhoneFunction();

        LogisticsOrderAddressDTO addressDTO = new LogisticsOrderAddressDTO();
        addressDTO.setRecipientProvince(recipientProvinceFunction.apply(purchaseAddress));
        addressDTO.setRecipientCity(recipientCityFunction.apply(purchaseAddress));
        addressDTO.setRecipientArea(recipientAreaFunction.apply(purchaseAddress));
        addressDTO.setRecipientAddress(recipientAddressFunction.apply(purchaseAddress));
        addressDTO.setRecipientName(recipientNameFunction.apply(purchaseAddress));
        addressDTO.setRecipientPhone(recipientPhoneFunction.apply(purchaseAddress));
        addressDTO.setSenderProvince(addressVo.getProvince());
        addressDTO.setSenderCity(addressVo.getCity());
        addressDTO.setSenderArea(addressVo.getArea());
        addressDTO.setSenderAddress(addressVo.getAddress());
        addressDTO.setSenderName(addressVo.getSenderName());
        addressDTO.setSenderPhone(addressVo.getSenderPhone());
        logisticsOrderDTO.setOrderAddress(addressDTO);
        return logisticsOrderDTO;
    }
}
