package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: Duke
 * @Description:
 * @Date: Created in 上午11:42 2018/9/30
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled(){
        long seckillId = 1001L;
        long userPhone = 18363033495L;
        int result = successKilledDao.insertSuccessKilled(seckillId,userPhone);
        System.out.println("result=" + result);
    }

    @Test
    public void queryByIdWithSeckill(){
        long seckillId = 1001L;
        long userPhone = 18363033495L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}
