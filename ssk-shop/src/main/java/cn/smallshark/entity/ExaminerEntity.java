package cn.smallshark.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 examiner
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-29 00:39:33
 */
public class ExaminerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //客户编号
    private Integer id;
    //客户姓名
    private String name;
    //0：代表女，1:代表男，2：代表没设置
    private Integer sex;
    //
    private String phone;
    //
    private Integer provinceId;
    //
    private String province;
    //
    private Integer cityId;
    //
    private String city;
    //
    private Integer countryId;
    //
    private String country;
    //
    private String addressDetail;

    /**
     * 设置：客户编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：客户编号
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：客户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：客户姓名
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：0：代表女，1:代表男，2：代表没设置
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取：0：代表女，1:代表男，2：代表没设置
     */
    public Integer getSex() {
        return sex;
    }
    /**
     * 设置：
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置：
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取：
     */
    public Integer getProvinceId() {
        return provinceId;
    }
    /**
     * 设置：
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取：
     */
    public Integer getCityId() {
        return cityId;
    }
    /**
     * 设置：
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * 获取：
     */
    public Integer getCountryId() {
        return countryId;
    }
    /**
     * 设置：
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取：
     */
    public String getCountry() {
        return country;
    }
    /**
     * 设置：
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    /**
     * 获取：
     */
    public String getAddressDetail() {
        return addressDetail;
    }
}
