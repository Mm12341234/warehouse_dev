package cn.smallshark.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 报警信息实体类 WarnEntity
 * @Author carvin
 * @Date 2018.12.25
 */
@Data
public class WarnEntity {
    // 主键
    String id;
    // 当前食品id
    Integer currentFoodId;
    // 报警类型 0：未设置、1：温度报警、2：保质期 （默认为未设置）
    Integer type;
    // 报警货架id
    Integer shelvesId;
    // 报警房间id
    Integer roomId;
    // 报警仓库id
    Integer warehouseId;
    // 报警时间
    Date time;
    // 备注
    String remark;

}
