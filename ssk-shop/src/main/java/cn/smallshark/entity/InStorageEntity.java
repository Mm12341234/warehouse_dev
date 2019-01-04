package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 in_storage
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Data
public class InStorageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //交易单编号
    private String no;
    //客户的ID
    private Integer customerId;
    //客户的名字
    private String customerName;
    //分类的名字
    private String cateName;
    //分类的ID
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
    //
    private String checkName;

    //所在的仓库id
    private Integer warehouseId;

    private Integer roomId;

    //仓库的名字
    private String warehouseName;
    //房间的名字
    private String roomName;
    //货架的名字
    private String shelvesName;
    //创建的时间
    private Date createTime;

}
