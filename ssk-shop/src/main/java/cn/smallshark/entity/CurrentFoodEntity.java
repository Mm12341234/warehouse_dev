package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 current_food
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
@Data
public class CurrentFoodEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //交易单编号
    private String no;
    //
    //客户的名字
    private String customerName;
    //分类的名字
    private String cateName;
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
    private String checkName;
    //入库的时间
    private Date instorageTime;
    //审核时间
    private Date checkTime;

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

    //交易单的状态
    private Integer status;
    //是否出库
    private Integer isOutstorage;


}
