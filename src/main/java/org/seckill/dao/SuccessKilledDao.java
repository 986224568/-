package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     * @Param  : [seckillId, userPhone]
     * @Return : int 如果影响行数=1，表示更新的记录行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
    /**
     * 根据Id查询SuccessKilled并携带秒杀产品对象
     * @Param  : [seckillId]
     * @Return : SuccessKilled
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);


}
