package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 warm
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2019-01-05 10:25:46
 */
@Data
public class WarmEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private Integer currentFoodId;
    //报警的类型：0：未设置、1：温度报警、2：保质期
    private Integer type;
    //报警的仓库
    private Integer shelvesId;
    //
    private Integer roomId;
    //
    private Integer warehouseId;
    //
    private Date time;
    //
    private String remark;

    //食品的名称
    private String name;
    //仓库的名称
    private String warehouseName;
    //房间的名称
    private String roomName;
    //货架的名称
    private String shelvesName;
}
