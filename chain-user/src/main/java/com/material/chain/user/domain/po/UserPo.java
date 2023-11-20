package com.material.chain.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
    * 用户表
    */
@TableName(value = "t_user")
public class UserPo {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private Long departmentId;

    /**
     * 班组id
     */
    @TableField(value = "class_id")
    private Long classId;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 状态 1:正常 2:删除
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 创建人id
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

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

    public static final String COL_ACCOUNT = "account";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_PASSWORD = "password";

    public static final String COL_DEPARTMENT_ID = "department_id";

    public static final String COL_CLASS_ID = "class_id";

    public static final String COL_PHONE = "phone";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_ID = "create_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_ID = "update_id";

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取部门id
     *
     * @return department_id - 部门id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门id
     *
     * @param departmentId 部门id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取班组id
     *
     * @return class_id - 班组id
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 设置班组id
     *
     * @param classId 班组id
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取状态 1:正常 2:删除
     *
     * @return status - 状态 1:正常 2:删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态 1:正常 2:删除
     *
     * @param status 状态 1:正常 2:删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取创建人id
     *
     * @return create_id - 创建人id
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * 设置创建人id
     *
     * @param createId 创建人id
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人id
     *
     * @return update_id - 修改人id
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * 设置修改人id
     *
     * @param updateId 修改人id
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }
}