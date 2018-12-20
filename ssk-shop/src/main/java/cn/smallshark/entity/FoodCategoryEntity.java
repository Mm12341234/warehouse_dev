package cn.smallshark.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 food_category
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:25
 */
public class FoodCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //名称
    private String name;
    //
    private Integer parentId;
    //等级
    private Integer level;
    //备注
    private String remark;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：
     */
    public Integer getParentId() {
        return parentId;
    }
    /**
     * 设置：等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取：等级
     */
    public Integer getLevel() {
        return level;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }
}
