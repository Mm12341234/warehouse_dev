package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 foodtemperature
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:57:09
 */
@Data
public class FoodtemperatureEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 食品id
    private Integer foodid;
    // 食品温度
    private Double foodtemperature;
    // 当前温度时间点
    private Date temperaturetime;

}
