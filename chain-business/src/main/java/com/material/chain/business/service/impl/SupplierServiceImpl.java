package com.material.chain.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.business.SupplierEnum;
import com.material.chain.business.domain.dto.SupplierDTO;
import com.material.chain.business.domain.po.SupplierAddressPo;
import com.material.chain.business.domain.po.SupplierPo;
import com.material.chain.business.domain.vo.SupplierAddressVo;
import com.material.chain.business.domain.vo.SupplierVo;
import com.material.chain.business.mapper.SupplierAddressPoMapper;
import com.material.chain.business.mapper.SupplierPoMapper;
import com.material.chain.business.service.SupplierService;
import com.material.chain.common.doamin.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierPoMapper, SupplierPo> implements SupplierService {

    @Autowired
    private SupplierAddressPoMapper supplierAddressPoMapper;

    @Override
    public PageVo<SupplierVo> pageList(SupplierDTO dto) {
        //查询供应商分页
        LambdaQueryWrapper<SupplierVo> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(dto.getSupplierName()), SupplierVo::getSupplierName, dto.getSupplierName());
        Page<SupplierPo> page = this.page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        List<SupplierPo> supplierList = Optional.ofNullable(page).map(Page::getRecords).orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(supplierList)) {
            return new PageVo<>();
        }
        //获取供应商id集合
        List<Long> ids = supplierList.stream().map(SupplierPo::getId).collect(Collectors.toList());
        List<SupplierAddressPo> supplierAddressList = supplierAddressPoMapper.findAllBySupplierIds(ids);
        //组装数据
        List<SupplierVo> supplierVoList = new ArrayList<>();
        for (SupplierPo po : supplierList) {
            SupplierVo vo = new SupplierVo();
            vo.setSupplierId(po.getId());
            vo.setLevel(po.getLevel());
            vo.setSupplierName(po.getSupplierName());
            vo.setSubjectName(po.getSubjectName());
            vo.setSupplierTypeDesc(SupplierEnum.getValue(po.getSupplierType()));
            if (CollectionUtils.isNotEmpty(supplierAddressList)) {
                //根据供应商id筛选出供应商地址
                List<SupplierAddressPo> filterAddressList = supplierAddressList.stream().filter(address -> Objects.equals(address.getSupplierId(), po.getId())).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(filterAddressList)) {
                    List<SupplierAddressVo> addressList = filterAddressList.stream().map(d -> {
                        SupplierAddressVo addressVo = new SupplierAddressVo();
                        addressVo.setAddress(d.getAddress());
                        addressVo.setAddressId(d.getId());
                        addressVo.setSupplierId(d.getSupplierId());
                        addressVo.setProvince(d.getProvince());
                        addressVo.setCity(d.getCity());
                        addressVo.setArea(d.getArea());
                        addressVo.setIsDefault(d.getIsDefault());
                        addressVo.setSenderName(d.getSenderName());
                        addressVo.setSenderPhone(d.getSenderPhone());
                        addressVo.setZipCode(d.getZipCode());
                        return addressVo;
                    }).collect(Collectors.toList());
                    vo.setAddressList(CollectionUtils.isNotEmpty(addressList) ? addressList : Collections.emptyList());
                }
            }
            supplierVoList.add(vo);
        }
        PageVo<SupplierVo> vo = new PageVo<>();
        vo.setPageNo(page.getCurrent());
        vo.setPageSize(page.getSize());
        vo.setRecords(supplierVoList);
        vo.setTotal(page.getTotal());
        return vo;
    }
}
