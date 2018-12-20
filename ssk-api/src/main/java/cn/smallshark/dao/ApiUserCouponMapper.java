package cn.smallshark.dao;

import cn.smallshark.entity.UserCouponVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2017-08-11 09:16:47
 */
public interface ApiUserCouponMapper extends BaseDao<UserCouponVo> {
    UserCouponVo queryByCouponNumber(@Param("coupon_number") String coupon_number);
}