package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 storage_item
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Data
public class StorageItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //交易单编号
    private String no;
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

   //出库的方式
    private Integer outType;
    //入库审核时间
    private Date inCheckTime;
    //入库时间
    private Date inStorageTime;

    //是否让其入库
    private Integer isStorage;

    //顾客的姓名
    private String customerName;
    //分类的名称
    private String categoryName;
    //审核员的名称
    private String examinerName;
    //仓库的名称
    private String warehouseName;
    //房间的名称
    private String roomName;
    //货架名称
    private String shelvesName;
}
