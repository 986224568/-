package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

public interface SeckillService {
    /*查询所有秒杀记录*/
    List<Seckill> getSecKillList();

    /**
     * 查询单个秒杀记录
     * @Param  : [seckillId]
     * @Return : org.seckill.entity.Seckill
     */
    Seckill getById(long seckillId);


    /**
     * @Param  : [seckillId]
     * @Return : void
     * 秒杀开启时输出秒杀接口的地址，否则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * @Param  : [seckillId, uesrPhone, md5]
     * @Return : void
     * 执行秒杀操作
     */
    SeckillExcution excuteSeckill(long seckillId, long uesrPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
