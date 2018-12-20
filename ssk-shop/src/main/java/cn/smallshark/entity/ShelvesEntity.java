package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 shelves
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 22:08:05
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
    private Integer roomId;
    //仓库的id
    private Integer wareId;
    //货架的层数
    private Integer floor;
    //房间名字
    private String roomName;
    //仓库名字
    private String warehouseName;

}
