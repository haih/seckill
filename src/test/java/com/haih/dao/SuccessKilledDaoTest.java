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
import com.haih.entity.SuccessKilled;

/**
 * 配置sprint与junit整合，junit启动时加载springIOC容器
 * spring-test,juint
 * @author haih
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit sprint配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(SuccessKilledDaoTest.class);
	//注入dao实现类依赖
	@Resource
	private SuccessKilledDao successkilledDao;
			
	
	@Test
	public void testinsertSuccessKilled() {
		long id = 1001;
		long phone=18022932882L;
		int insertCount = successkilledDao.insertSuccessKilled(id, phone);
		logger.info("insertCount = " + String.valueOf(insertCount));
	}

	@Test
	public void testqueryByIdWithSeckill() {
		long id = 1001;
		long phone=18022932882L;
		SuccessKilled successKilled = successkilledDao.queryByIdWithSeckill(id,phone);
		System.out.println(successKilled.getSeckllId());
		logger.info(successKilled.getSeckill().toString());
		/**
		 * [seckillId=1000, name=1000 sec kill iphone6, number=100, startTime=2016-05-17, endTime=2016-05-17, createTime=2016-05-17]
		 */
	}

}
