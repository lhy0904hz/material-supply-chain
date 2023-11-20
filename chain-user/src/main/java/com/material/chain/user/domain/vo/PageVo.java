package com.material.chain.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageVo<T> {

    /**
     * 总数
     */
    private Long total;

    /**
     * 当前页数
     */
    private Long pageNo;

    /**
     * 每页总数
     */
    private Long pageSize;

    /**
     * 数据
     */
    protected List<T> records;

}
