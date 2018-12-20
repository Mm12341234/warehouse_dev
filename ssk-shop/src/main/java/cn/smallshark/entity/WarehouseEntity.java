package cn.smallshark.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 warehouse
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:58
 */
public class WarehouseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //仓库编号
    private String id;
    //仓库名称
    private String name;
    //省份
    private String province;
    //城市
    private String city;
    //区域
    private String country;
    //省份
    private Integer provinceId;
    //城市
    private Integer cityId;
    //区域
    private Integer countryId;
    //详细地址
    private String addressDetail;
    //最大容量
    private BigDecimal maxContent;
    //存储房的数量
    private Integer roomNum;
    //仓库的经度
    private BigDecimal longitude;
    //仓库的纬度
    private BigDecimal dimension;
    //仓库的楼层
    private Integer floor;

    /**
     * 设置：仓库编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：仓库编号
     */
    public String getId() {
        return id;
    }
    /**
     * 设置：仓库名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：仓库名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取：省份
     */
    public String getProvince() {
        return province;
    }
    public Integer getProvinceId() {
        return provinceId;
    }
    /**
     * 设置：城市
     */
    public void setCity(String city) {
        this.city = city;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    /**
     * 获取：城市
     */
    public String getCity() {
        return city;
    }
    public Integer getCityId() {
        return cityId;
    }
    /**
     * 设置：区域
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * 设置：区域Id
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
    /**
     * 获取：区域
     */
    public String getCountry() {
        return country;
    }
    public Integer getCountryId() {
        return countryId;
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
     * 设置：存储房的数量
     */
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * 获取：存储房的数量
     */
    public Integer getRoomNum() {
        return roomNum;
    }
    /**
     * 设置：仓库的经度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取：仓库的经度
     */
    public BigDecimal getLongitude() {
        return longitude;
    }
    /**
     * 设置：仓库的纬度
     */
    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

    /**
     * 获取：仓库的纬度
     */
    public BigDecimal getDimension() {
        return dimension;
    }
    /**
     * 设置：仓库的楼层
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    /**
     * 获取：仓库的楼层
     */
    public Integer getFloor() {
        return floor;
    }
}
