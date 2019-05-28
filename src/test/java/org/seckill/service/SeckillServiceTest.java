package org.seckill.service;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    @Test
    public void testGetSeckillList() throws Exception{
        List<Seckill> list = seckillService.getSecKillList();
        logger.info("list={}",list);

    }
    @Test
    public void testGetById() throws Exception{
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    /*exposer=Exposer(exposed=true, md5=e3859d3649443f250ffa5bbc9202d4e1, seckillId=1000, now=0, start=0,*/
    @Test
    public void testExportSeckillUrl() throws Exception{
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);

    }
    @Test
    public void testExecuteSeckill() throws Exception{
         long id = 1000;
         long phone = 17819835223L;
         String md5 = "e3859d3649443f250ffa5bbc9202d4e1";
        try {
            SeckillExcution excution = seckillService.excuteSeckill(id,phone,md5);
            logger.info("result={}",excution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e1){
            logger.error(e1.getMessage());
        }

         /*result=SeckillExcution{seckillId=1000, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1000, userPhone=17819835223, state=0, createTime=Thu May 23 14:30:13 CST 2019}}*/
    }

    @Test
    public void testSeckillLogic() throws Exception{
        long id = 1003;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 13245678912L;
            String md5 = exposer.getMd5();
            try {
                SeckillExcution excution = seckillService.excuteSeckill(id,phone,md5);
                logger.info("result={}",excution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e1){
                logger.error(e1.getMessage());
            }
        }else{
            //秒杀未开启
            logger.info("exposer={}",exposer);
        }
    }

}