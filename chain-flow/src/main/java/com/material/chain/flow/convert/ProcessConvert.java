package com.material.chain.flow.convert;

import com.material.chain.flow.model.dto.CreateProcessDTO;
import com.material.chain.flow.model.po.ProcessPo;

public class ProcessConvert {

    public static ProcessPo convertCreateProcessDTO(CreateProcessDTO dto) {
        return ProcessPo
                .builder()
                .processName(dto.getProcessName())
                .createId(dto.getCreateId())
                .createTime(System.currentTimeMillis())
                .build();
    }
}
