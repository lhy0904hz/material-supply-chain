package com.material.chain.flow.convert;

import cn.hutool.core.collection.CollUtil;
import com.material.chain.common.enums.StatusEnum;
import com.material.chain.flow.model.dto.ProcessNodeDTO;
import com.material.chain.flow.model.po.ProcessNodePo;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessNodeConvert {

    /**
     * 转化ProcessNode对象
     * @param nodeList 流程节点集合
     * @param processId 流程id
     * @return List<ProcessNodePo>
     */
    public static List<ProcessNodePo> convertProcessNodeList(List<ProcessNodeDTO> nodeList, Long processId) {
        if (CollUtil.isEmpty(nodeList)) {
            return Collections.emptyList();
        }
        return nodeList.stream().map(node -> ProcessNodePo
                .builder()
                .processId(processId)
                .nodeName(node.getNodeName())
                .nodeType(node.getNodeType())
                .isAssign(node.getIsAssign())
                .roleIds(CollUtil.isNotEmpty(node.getRoleIds()) ? StringUtils.join(node.getRoleIds(), ",") : null)
                .userIds(CollUtil.isNotEmpty(node.getUserIds()) ? StringUtils.join(node.getUserIds(), ",") : null)
                .createId(node.getCreateId())
                .createTime(System.currentTimeMillis())
                .status(StatusEnum.NORMAL.getCode())
                .build()).collect(Collectors.toList());
    }
}
