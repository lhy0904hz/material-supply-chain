package com.material.chain.business.convert;

import com.material.chain.business.domain.function.LogisticsFunction;
import com.material.chain.business.domain.po.GlobalPurchaseAddressPo;
import com.material.chain.business.domain.po.GlobalPurchaseItemPo;
import com.material.chain.business.domain.po.GlobalPurchaseOrderPo;
import com.material.chain.business.domain.po.SupplierAddressPo;

import java.util.List;

public class LogisticsFunctionConvert {

    public static LogisticsFunction<GlobalPurchaseOrderPo, GlobalPurchaseAddressPo, GlobalPurchaseItemPo> buildLogisticsFromGlobal(GlobalPurchaseOrderPo purchaseOrderPo,
                                                                                                                                   GlobalPurchaseAddressPo addressPo,
                                                                                                                                   List<GlobalPurchaseItemPo> itemList,
                                                                                                                                   SupplierAddressPo supplierAddressPo,
                                                                                                                                   Long currentUserId,
                                                                                                                                   Long providerId) {
        LogisticsFunction<GlobalPurchaseOrderPo, GlobalPurchaseAddressPo, GlobalPurchaseItemPo> po = new LogisticsFunction<>();
        po.setPurchaseOrder(purchaseOrderPo);
        po.setPurchaseAddress(addressPo);
        po.setPurchaseItemList(itemList);
        po.setSupplierAddress(supplierAddressPo);

        po.setUserId(currentUserId);
        po.setSupplierId(supplierAddressPo.getSupplierId());
        po.setProviderId(providerId);

        po.setBusinessNoFunction(GlobalPurchaseOrderPo::getOrederNo);
        po.setRecipientProvinceFunction(GlobalPurchaseAddressPo::getProvince);
        po.setRecipientCityFunction(GlobalPurchaseAddressPo::getCity);
        po.setRecipientAreaFunction(GlobalPurchaseAddressPo::getArea);
        po.setRecipientNameFunction(GlobalPurchaseAddressPo::getRecipientName);
        po.setRecipientPhoneFunction(GlobalPurchaseAddressPo::getRecipientPhone);
        po.setRecipientAddressFunction(GlobalPurchaseAddressPo::getAddress);

        po.setMaterialNameFunction(GlobalPurchaseItemPo::getMaterialName);
        po.setThicknessFunction(GlobalPurchaseItemPo::getThickness);
        po.setWidthFunction(GlobalPurchaseItemPo::getWidth);
        po.setLengthFunction(GlobalPurchaseItemPo::getLength);
        po.setWeightFunction(GlobalPurchaseItemPo::getWeight);
        po.setUnitPriceFunction(GlobalPurchaseItemPo::getUnitPrice);
        po.setQuantityFunction(GlobalPurchaseItemPo::getQuantity);

        return po;
    }

}
