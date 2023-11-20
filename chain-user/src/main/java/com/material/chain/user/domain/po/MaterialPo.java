package com.material.chain.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
    * 大船物料表
    */
@Data
@TableName(value = "t_material")
public class MaterialPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 船名
     */
    @TableField(value = "ship_name")
    private String shipName;

    /**
     * 批量
     */
    @TableField(value = "batch")
    private Integer batch;

    /**
     * 分段
     */
    @TableField(value = "subsection")
    private Integer subsection;

    /**
     * 编码件号
     */
    @TableField(value = "materiel_code")
    private String materielCode;

    /**
     * 下料
     */
    @TableField(value = "cutting")
    private String cutting;

    /**
     * 材质
     */
    @TableField(value = "material")
    private String material;

    /**
     * 厚度（mm）
     */
    @TableField(value = "thickness")
    private BigDecimal thickness;

    /**
     * 宽度（mm）
     */
    @TableField(value = "width")
    private BigDecimal width;

    /**
     * 长度（mm）
     */
    @TableField(value = "`length`")
    private BigDecimal length;

    /**
     * 物料类型
     */
    @TableField(value = "materiel_type")
    private String materielType;

    /**
     * 加工
     */
    @TableField(value = "machining")
    private String machining;

    /**
     * 零件数量
     */
    @TableField(value = "part_number")
    private Integer partNumber;

    /**
     * 发料数量
     */
    @TableField(value = "issue_number")
    private Integer issueNumber;

    /**
     * 重量（t）
     */
    @TableField(value = "total_weight")
    private BigDecimal totalWeight;

    /**
     * 1：大船物料 2：中远物料 3：天津物料
     */
    @TableField(value = "material_classify")
    private Integer materialClassify;

    /**
     * 米数
     */
    @TableField(value = "meter_number")
    private BigDecimal meterNumber;

    /**
     * 状态1:未发料 2:已发料
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 创建人id
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

    /**
     * 修改人id
     */
    @TableField(value = "update_id")
    private Long updateId;

    public static final String COL_ID = "id";

    public static final String COL_SHIP_NAME = "ship_name";

    public static final String COL_BATCH = "batch";

    public static final String COL_SUBSECTION = "subsection";

    public static final String COL_MATERIEL_CODE = "materiel_code";

    public static final String COL_CUTTING = "cutting";

    public static final String COL_MATERIAL = "material";

    public static final String COL_THICKNESS = "thickness";

    public static final String COL_WIDTH = "width";

    public static final String COL_LENGTH = "length";

    public static final String COL_MATERIEL_TYPE = "materiel_type";

    public static final String COL_MACHINING = "machining";

    public static final String COL_PART_NUMBER = "part_number";

    public static final String COL_ISSUE_NUMBER = "issue_number";

    public static final String COL_TOTAL_WEIGHT = "total_weight";

    public static final String COL_MATERIAL_CLASSIFY = "material_classify";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_ID = "create_id";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_ID = "update_id";
}