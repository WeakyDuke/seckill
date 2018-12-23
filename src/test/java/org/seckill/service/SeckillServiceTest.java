package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RePeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);

    }

    /**
     * 测试代码完整逻辑，注意课重复执行
     * @throws Exception
     */
    @Test
    public void seckillUrlLogic() throws Exception {
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            logger.info("exposer={}", exposer);
            long userPhone = 18363033496L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }catch (RePeatKillException e){
                logger.error(e.getMessage());
            }
        }else {
            // 秒杀未开启
            logger.info("exposer={}", exposer);
        }
    }

    @Test
    public void executeSeckill() throws Exception {
        long seckillId = 1000;
        long userPhone = 18363033496L;
        String md5 = "12412b7c99196f1b5b27ca2da0c5e80d";
        SeckillExecution seckillExecution = null;
        try {
            seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }catch (RePeatKillException e){
            logger.error(e.getMessage());
        }
        logger.info("seckillExecution={}", seckillExecution);
        /**
         * 13:04:58.538 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
         13:04:58.547 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d]
         13:04:58.556 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@19593091 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@5d39f2d8]] will be managed by Spring
         13:04:58.561 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>  Preparing: UPDATE seckill SET number = number - 1 WHERE seckill_id = ? AND start_time <= ? AND end_time >= ? AND number > 0
         13:04:58.607 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1000(Long), 2018-12-16 13:04:58.532(Timestamp), 2018-12-16 13:04:58.532(Timestamp)
         13:04:58.618 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
         13:04:58.618 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d]
         13:04:58.618 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d] from current transaction
         13:04:58.618 [main] DEBUG o.s.d.S.insertSuccessKilled - ==>  Preparing: insert ignore into success_killed(seckill_id, user_phone, state, create_time) values (?, ?, 0, now())
         13:04:58.619 [main] DEBUG o.s.d.S.insertSuccessKilled - ==> Parameters: 1000(Long), 18363033496(Long)
         13:04:58.628 [main] DEBUG o.s.d.S.insertSuccessKilled - <==    Updates: 1
         13:04:58.649 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d]
         13:04:58.650 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d] from current transaction
         13:04:58.654 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id",
         s.number "seckill.number", s.name "seckill.name", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time"
         from success_killed sk
         inner join seckill s on s.seckill_id = sk.seckill_id where sk.seckill_id = ? and sk.user_phone = ?;
         13:04:58.654 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 1000(Long), 18363033496(Long)
         13:04:58.683 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
         13:04:58.690 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d]
         13:04:58.692 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d]
         13:04:58.693 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d]
         13:04:58.693 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34b9fc7d]
         13:04:58.699 [main] INFO  o.seckill.service.SeckillServiceTest - seckillExecution=SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功',
         successKilled=SuccessKilled{seckillId=1000, userPhone=18363033496, state=0, createTime=Mon Dec 17 03:04:58 CST 2018,
         seckill=Seckill{seckillId=1000, name='1000元秒杀iPhone6', number=99, startTime=Sat Dec 15 14:00:00 CST 2018, endTime=Thu Dec 20 14:00:00 CST 2018, createTime=Fri Aug 10 13:12:23 CST 2018}}}
         */
    }
}