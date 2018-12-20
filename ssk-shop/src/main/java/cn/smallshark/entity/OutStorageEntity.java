package cn.smallshark.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 out_storage
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
public class OutStorageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //交易单编号
    private Integer no;
    //
    private Integer customerId;
    //
    private Integer categoryId;
    //数量
    private BigDecimal num;
    //货架的id
    private Integer shelvesId;
    //货架的层数
    private Integer shelvesNum;
    //boolean，0：代表自动，1：代表手动
    private Integer inType;
    //审核员
    private Integer checkId;
    //int，0：代表正常，1：代表腐损
    private Integer isNormal;
    //
    private Date finishTime;

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
     * 设置：交易单编号
     */
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * 获取：交易单编号
     */
    public Integer getNo() {
        return no;
    }
    /**
     * 设置：
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取：
     */
    public Integer getCustomerId() {
        return customerId;
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
     * 设置：数量
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    /**
     * 获取：数量
     */
    public BigDecimal getNum() {
        return num;
    }
    /**
     * 设置：货架的id
     */
    public void setShelvesId(Integer shelvesId) {
        this.shelvesId = shelvesId;
    }

    /**
     * 获取：货架的id
     */
    public Integer getShelvesId() {
        return shelvesId;
    }
    /**
     * 设置：货架的层数
     */
    public void setShelvesNum(Integer shelvesNum) {
        this.shelvesNum = shelvesNum;
    }

    /**
     * 获取：货架的层数
     */
    public Integer getShelvesNum() {
        return shelvesNum;
    }
    /**
     * 设置：boolean，0：代表自动，1：代表手动
     */
    public void setInType(Integer inType) {
        this.inType = inType;
    }

    /**
     * 获取：boolean，0：代表自动，1：代表手动
     */
    public Integer getInType() {
        return inType;
    }
    /**
     * 设置：审核员
     */
    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    /**
     * 获取：审核员
     */
    public Integer getCheckId() {
        return checkId;
    }
    /**
     * 设置：int，0：代表正常，1：代表腐损
     */
    public void setIsNormal(Integer isNormal) {
        this.isNormal = isNormal;
    }

    /**
     * 获取：int，0：代表正常，1：代表腐损
     */
    public Integer getIsNormal() {
        return isNormal;
    }
    /**
     * 设置：
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取：
     */
    public Date getFinishTime() {
        return finishTime;
    }
}
