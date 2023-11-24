package com.material.chain.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.base.exception.ApiException;
import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.business.domain.dto.MaterialDTO;
import com.material.chain.business.domain.dto.SupplierMaterialDTO;
import com.material.chain.business.domain.po.MaterialInventoryPo;
import com.material.chain.business.domain.po.MaterialPo;
import com.material.chain.business.domain.vo.MaterialGenerateVo;
import com.material.chain.business.domain.vo.MaterialInventoryVo;
import com.material.chain.business.mapper.MaterialInventoryPoMapper;
import com.material.chain.business.mapper.MaterialPoMapper;
import com.material.chain.business.service.MaterialService;
import com.material.chain.business.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialPoMapper, MaterialPo> implements MaterialService {

    @Autowired
    private MaterialPoMapper materialPoMapper;
    @Autowired
    private MaterialInventoryPoMapper materialInventoryPoMapper;

    /**
     * 添加物料
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addMaterial(SupplierMaterialDTO dto) {
        List<MaterialDTO> materialList = dto.getMaterialList();
        if (CollectionUtils.isEmpty(materialList)) {
            throw new ApiException("物料信息不能为空");
        }
        Long currentUserId = AppContextUtil.getCurrentUserId();
        long timeMillis = System.currentTimeMillis();
        for (MaterialDTO materialDTO : materialList) {
            MaterialPo po = new MaterialPo();
            po.setSupplierId(dto.getSupplierId());
            po.setMaterialCode(materialDTO.getMaterialCode());
            po.setSubsection(materialDTO.getSubsection());
            po.setLength(materialDTO.getLength());
            po.setWidth(materialDTO.getWidth());
            po.setMachining(materialDTO.getMachining());
            po.setMaterialName(materialDTO.getMaterialName());
            po.setMaterialType(materialDTO.getMaterialType());
            po.setPartNum(materialDTO.getPartNum());
            po.setPrice(materialDTO.getPrice());
            po.setThickness(materialDTO.getThickness());
            po.setTotalWeight(materialDTO.getTotalWeight());
            po.setCreateId(currentUserId);
            po.setCreateTime(timeMillis);
            po.setUpdateId(currentUserId);
            po.setUpdateTime(timeMillis);
            boolean save = this.save(po);
            if (save) {
                MaterialInventoryPo inventoryPo = new MaterialInventoryPo();
                inventoryPo.setInventoryNumber(materialDTO.getInventoryNumber());
                inventoryPo.setMaterialId(po.getId());
                inventoryPo.setSupplierId(dto.getSupplierId());
                inventoryPo.setCreateId(currentUserId);
                inventoryPo.setCreateTime(timeMillis);
                inventoryPo.setUpdateId(currentUserId);
                inventoryPo.setUpdateTime(timeMillis);
                materialInventoryPoMapper.insert(inventoryPo);
            }
        }
        return true;
    }

    /**
     * 随机生成物料编码
     */
    @Override
    public MaterialGenerateVo generateMaterialCode(Long supplierId) {
        Integer subsections = materialPoMapper.findSubsectionBySupplierId(supplierId);
        MaterialGenerateVo vo = new MaterialGenerateVo();
        StringBuilder firstCode;
        StringBuilder secondCode;
        if (Objects.isNull(subsections) || subsections == 0) {
            Integer defaultSubsection = 1000;
            firstCode = new StringBuilder(defaultSubsection.toString());
            secondCode = new StringBuilder(defaultSubsection.toString());
            vo.setSubsection(defaultSubsection);
        } else {
            //保证原子性加1
            AtomicInteger atomicInteger = new AtomicInteger(subsections);
            Integer newSubsections = atomicInteger.incrementAndGet();
            //这个加任意数字
            //int newSubsections = atomicInteger.addAndGet(2);
            firstCode = new StringBuilder(newSubsections.toString());
            secondCode = new StringBuilder(newSubsections.toString());
        }
        char letter = RandomUtil.generateRandomLetter();
        int oneToTen = RandomUtil.randomNumberOneToTen();
        int oneToHundred = RandomUtil.randomNumberOneToHundred();

        firstCode.append("-")
                .append("BL")
                .append(oneToTen)
                .append(letter)
                .append(oneToTen)
                .append("-")
                .append("T")
                .append(oneToHundred)
                .append("AH");

        secondCode.append("-")
                .append("BL")
                .append(oneToTen)
                .append(letter)
                .append(oneToTen)
                .append("-")
                .append("T")
                .append(oneToHundred)
                .append("AZ");
        vo.setMaterialName("T型材");
        vo.setMaterialCodeList(Arrays.asList(firstCode.toString(), secondCode.toString()));
        return vo;
    }

    /**
     * 根据id集合查询物料库存
     */
    @Override
    public List<MaterialInventoryPo> getMaterialInventoryByIds(List<Long> materialIds) {
        return materialInventoryPoMapper.findAllInMaterialIds(materialIds);
    }

    @Override
    public MaterialPo getMaterialCodeById(Long id) {
        return materialPoMapper.selectById(id);
    }

    /**
     * 修改库存
     * @param id 主键id
     * @param inventoryNumber 库存值
     */
    @Override
    public void updateMaterialInventory(Long id, Integer inventoryNumber) {
        materialInventoryPoMapper.updateIncInventoryNumberById(id, inventoryNumber);
    }
}
