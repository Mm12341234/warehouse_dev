package cn.smallshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 * 表名 out_storage
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Data
public class AutoOutStorageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //交易单编号
    private Integer no;
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

    //客户的名字
    private String customerName;
    private String examinerName;
    private String cateName;
}
