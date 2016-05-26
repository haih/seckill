 package com.haih.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haih.entity.Seckill;

/**
 * 配置sprint与junit整合，junit启动时加载springIOC容器
 * spring-test,juint
 * @author haih
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit sprint配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(SeckillDaoTest.class);
	//注入dao实现类依赖
	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000, killTime);
		logger.info("testReduceNumber = " + String.valueOf(updateCount));
	}

	@Test
	public void testQueryById() {
		long id=1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		logger.info(seckill.toString());
		/**
		 * [seckillId=1000, name=1000 sec kill iphone6, number=100, startTime=2016-05-17, endTime=2016-05-17, createTime=2016-05-17]
		 */
	}

	@Test
	public void testQueryAll() {
		List<Seckill> seckills = seckillDao.queryAll(0, 10);
		
		for (Seckill seckill:seckills){
			logger.info(seckill.toString());
		}
	}

}
