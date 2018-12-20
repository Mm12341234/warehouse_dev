package cn.smallshark.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 suitable_temperature
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:26
 */
public class SuitableTemperatureEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private String name;
    //
    private Integer categoryId;
    //
    private Double minTemperature;
    //
    private Double maxTemperature;
    //
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
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：
     */
    public Integer getCategoryId() {
        return categoryId;
    }
    /**
     * 设置：
     */
    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    /**
     * 获取：
     */
    public Double getMinTemperature() {
        return minTemperature;
    }
    /**
     * 设置：
     */
    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    /**
     * 获取：
     */
    public Double getMaxTemperature() {
        return maxTemperature;
    }
    /**
     * 设置：
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：
     */
    public String getRemark() {
        return remark;
    }
}
