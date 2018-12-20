package cn.smallshark.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 room
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
public class RoomEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //房间编号
    private String id;
    //房间名称
    private String name;
    //详细地址
    private String addressDetail;
    //最大容量
    private BigDecimal maxContent;
    //仓库的id
    private String warehouseId;
    //属于仓库的第几层楼
    private Integer floorNum;
    //仓库的名字
    private String warehouseName;
    //仓库的地址
    private String warehouseAddress;
    /**
     * 设置仓库名字
     */
    public void setWarehouseName(String warehouseName){
        this.warehouseName=warehouseName;
    }
    /**
     * 获得：仓库名字
     */
    public String getWarehouseName(){
        return this.warehouseName;
    }
    /**
     * 设置仓库名字
     */
    public void setWarehouseAddress(String warehouseAddress){
        this.warehouseAddress=warehouseAddress;
    }
    /**
     * 获得：仓库名字
     */
    public String getWarehouseAddress(){
        return this.warehouseAddress;
    }

    /**
     * 设置：房间编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：房间编号
     */
    public String getId() {
        return id;
    }
    /**
     * 设置：房间名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：房间名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：详细地址
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    /**
     * 获取：详细地址
     */
    public String getAddressDetail() {
        return addressDetail;
    }
    /**
     * 设置：最大容量
     */
    public void setMaxContent(BigDecimal maxContent) {
        this.maxContent = maxContent;
    }

    /**
     * 获取：最大容量
     */
    public BigDecimal getMaxContent() {
        return maxContent;
    }
    /**
     * 设置：仓库的id
     */
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取：仓库的id
     */
    public String getWarehouseId() {
        return warehouseId;
    }
    /**
     * 设置：属于仓库的第几层楼
     */
    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    /**
     * 获取：属于仓库的第几层楼
     */
    public Integer getFloorNum() {
        return floorNum;
    }
}
