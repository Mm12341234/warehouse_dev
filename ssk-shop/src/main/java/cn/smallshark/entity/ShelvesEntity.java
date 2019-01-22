package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实体
 * 表名 shelves
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2019-01-09 00:54:34
 */
@Data
public class ShelvesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //货架名称
    private String name;
    //详细地址
    private String addressDetail;
    //
    private BigDecimal maxContent;
    //仓库的id
    private Integer wareId;
    //仓库的id
    private Integer roomId;
    //货架的层数
    private Integer floor;
    //货架所在仓库的楼层
    private Integer warehouseFloor;
    //仓库所在的行
    private Integer row;
    //仓库所在的列
    private Integer column;
    //仓库名称
    private String roomName;
    //房间的名称
    private String warehouseName;

}
