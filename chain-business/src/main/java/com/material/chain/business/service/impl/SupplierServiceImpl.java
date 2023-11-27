package com.material.chain.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.base.exception.ApiException;
import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.business.enums.SupplierEnum;
import com.material.chain.business.domain.dto.SupplierAddressDTO;
import com.material.chain.business.domain.dto.SupplierDTO;
import com.material.chain.business.domain.po.SupplierAddressPo;
import com.material.chain.business.domain.po.SupplierPo;
import com.material.chain.business.domain.vo.SupplierAddressVo;
import com.material.chain.business.domain.vo.SupplierVo;
import com.material.chain.business.mapper.SupplierAddressPoMapper;
import com.material.chain.business.mapper.SupplierPoMapper;
import com.material.chain.business.service.SupplierService;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 供应商
 */
@Slf4j
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierPoMapper, SupplierPo> implements SupplierService {

    @Autowired
    private SupplierAddressPoMapper supplierAddressPoMapper;

    /**
     * 分页列表
     */
    @Override
    public PageVo<SupplierVo> pageList(SupplierDTO dto) {
        //查询供应商分页
        LambdaQueryWrapper<SupplierPo> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(dto.getSupplierName()), SupplierPo::getSupplierName, dto.getSupplierName());
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

    /**
     * 添加供应商
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addSupplier(SupplierDTO dto) {
        if (checkSupplierIsRepeat(dto.getSupplierName(), dto.getSupplierId())) {
            throw new ApiException("供应商名称重复");
        }
        Long currentUserId = AppContextUtil.getCurrentUserId();
        long timeMillis = System.currentTimeMillis();
        //保存供应商信息
        SupplierPo po = buildSupplierPo(dto, currentUserId, timeMillis);
        this.save(po);
        //保存供应商地址
        saveSupplierAddress(dto.getAddressList(), po.getId(), currentUserId, timeMillis);
        return true;
    }

    /**
     * 编辑供应商
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean editSupplier(SupplierDTO dto) {
        if (checkSupplierIsRepeat(dto.getSupplierName(), dto.getSupplierId())) {
            throw new ApiException("供应商名称重复");
        }
        Long currentUserId = AppContextUtil.getCurrentUserId();
        long timeMillis = System.currentTimeMillis();
        //编辑供应商
        SupplierPo po = buildSupplierPo(dto, currentUserId, timeMillis);
        this.updateById(po);
        //编辑供应商地址
        supplierAddressPoMapper.updateBatch(buildSupplierAddressPo(dto.getAddressList(), po.getId(), currentUserId, timeMillis));
        return null;
    }

    /**
     * 供应商详情
     */
    @Override
    public SupplierVo detail(Long supplierId) {
        SupplierPo supplierPo = this.getById(supplierId);
        if (Objects.isNull(supplierPo)) {
            throw new ApiException("供应商信息不存在");
        }
        SupplierVo vo = new SupplierVo();
        vo.setSupplierId(supplierPo.getId());
        vo.setSupplierName(supplierPo.getSupplierName());
        vo.setSubjectName(supplierPo.getSubjectName());
        vo.setSupplierTypeDesc(SupplierEnum.getValue(supplierPo.getSupplierType()));
        vo.setLevel(supplierPo.getLevel());

        //供应商地址信息
        LambdaQueryWrapper<SupplierAddressPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SupplierAddressPo::getSupplierId, supplierId);
        wrapper.eq(SupplierAddressPo::getIsDefault, 0);
        List<SupplierAddressPo> supplierAddressList = supplierAddressPoMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(supplierAddressList)) {
            throw new ApiException("供应商地址不存在");
        }
        List<SupplierAddressVo> addressList = supplierAddressList.stream().map(address -> {
            SupplierAddressVo addressVo = new SupplierAddressVo();
            addressVo.setAddressId(address.getId());
            addressVo.setSupplierId(address.getSupplierId());
            addressVo.setProvince(address.getProvince());
            addressVo.setCity(address.getCity());
            addressVo.setArea(address.getArea());
            addressVo.setSenderName(address.getSenderName());
            addressVo.setSenderPhone(address.getSenderPhone());
            addressVo.setIsDefault(address.getIsDefault());
            addressVo.setZipCode(address.getZipCode());
            addressVo.setAddress(address.getAddress());
            return addressVo;
        }).collect(Collectors.toList());
        vo.setAddressList(addressList);

        return vo;
    }

    /**
     * 封装供应商数据
     */
    private SupplierPo buildSupplierPo(SupplierDTO dto, Long currentUserId, long timeMillis) {
        SupplierPo po = new SupplierPo();
        po.setId(dto.getSupplierId());
        po.setSupplierName(dto.getSupplierName());
        po.setLevel(dto.getLevel());
        po.setSupplierType(dto.getSupplierType());
        po.setSubjectName(dto.getSubjectName());
        po.setCreateId(currentUserId);
        po.setUpdateId(currentUserId);
        po.setCreateTime(timeMillis);
        po.setUpdateTime(timeMillis);
        po.setStatus(StatusEnum.NORMAL.getCode());
        return po;
    }

    /**
     * 保存供应商地址
     * @param addressList 地址
     * @param supplierId 供应商id
     * @param currentUserId 当前用户id
     * @param timeMillis 当前时间戳
     */
    private void saveSupplierAddress(List<SupplierAddressDTO> addressList, Long supplierId, Long currentUserId, long timeMillis) {
        if (CollectionUtils.isEmpty(addressList)) {
            throw new ApiException("供应商地址不能为空");
        }
        supplierAddressPoMapper.batchInsert(buildSupplierAddressPo(addressList, supplierId, currentUserId, timeMillis));
    }

    /**
     * 封装供应商地址数据
     */
    private List<SupplierAddressPo> buildSupplierAddressPo(List<SupplierAddressDTO> addressList, Long supplierId, Long currentUserId, long timeMillis) {
        List<SupplierAddressPo> supplierAddressList = addressList.stream().map(address -> {
            SupplierAddressPo po = new SupplierAddressPo();
            po.setId(address.getAddressId());
            po.setAddress(address.getAddress());
            po.setSupplierId(supplierId);
            po.setProvince(address.getProvince());
            po.setCity(address.getCity());
            po.setArea(address.getArea());
            po.setIsDefault(address.getIsDefault());
            po.setSenderName(address.getSenderName());
            po.setSenderPhone(address.getSenderPhone());
            po.setZipCode(address.getZipCode());
            po.setCreateId(currentUserId);
            po.setUpdateId(currentUserId);
            po.setCreateTime(timeMillis);
            po.setUpdateTime(timeMillis);
            return po;
        }).collect(Collectors.toList());
        long count = supplierAddressList.stream().filter(address -> address.getIsDefault() == 0).count();
        if (count != 1) {
            throw new ApiException("只能设置一个默认地址");
        }
        return supplierAddressList;
    }

    /**
     * 校验供应商名称是否重复 true：重复 false：不重复
     */
    private boolean checkSupplierIsRepeat(String supplierName, Long supplierId) {
        LambdaQueryWrapper<SupplierPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SupplierPo::getSupplierName, supplierName);
        wrapper.eq(SupplierPo::getStatus, StatusEnum.NORMAL.getCode());
        List<SupplierPo> list = this.list(wrapper);
        //新增时校验，没有供应商ID
        if (CollectionUtils.isNotEmpty(list) && Objects.isNull(supplierId)) {
            return true;
        }
        //编辑时校验，有供应商ID
        if (CollectionUtils.isNotEmpty(list) && Objects.nonNull(supplierId)) {
            //相同id的数据remove，再跟剩下的数据进行对比是否有重复(用于编辑时校验)
            list.removeIf(d -> Objects.equals(d.getId(), supplierId));
            list = list.stream().filter(d -> StringUtils.equals(d.getSupplierName(), supplierName)).collect(Collectors.toList());
            return CollectionUtils.isNotEmpty(list);
        }
        return false;
    }
}
