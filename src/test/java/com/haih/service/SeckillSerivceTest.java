package com.haih.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sun.util.logging.resources.logging;

import com.haih.dto.Exposer;
import com.haih.dto.SeckillExecution;
import com.haih.entity.Seckill;
import com.haih.exception.RepeatKillExecption;
import com.haih.exception.SeckillCloseException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})

public class SeckillSerivceTest {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillSerivce seckillService;
	
	@Test
	public void testGetSeckillList() throws Exception {
		List<Seckill> list = seckillService.getSeckillList();
		Logger.info("list = {}",list);
		
	}

	@Test 
	public void testGetById() throws Exception {
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		Logger.info("seckill={}",seckill);
	}

	@Test
	public void testExportSeckillUrl() throws Exception {
		long id = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		Logger.info("exposer={}",exposer);
		// 		exposer=Exposer [
		//		exposed=true, 
		//		md5=4d37f1eec4119631e5b8366ae1162274, 
		//		seckillId=1000,
		//		start=0, 
		//		now=0, 
		//		end=0]
	}
 
	@Test
	public void testExecuteSeckill() throws Exception {
		long id = 1001;
		long phone = 131414223L;
		String md5 = "4d37f1eec4119631e5b8366ae1162274";
		SeckillExecution execution;
		try {
			execution = seckillService.executeSeckill(id, phone, md5);
			Logger.info("result={}",execution);
		} catch (SeckillCloseException e) {
			Logger.error(e.getMessage());
		} catch (RepeatKillExecption e) {
			Logger.error(e.getMessage());
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
	
	@Test
	//完整的秒杀case，结合上面两个case
	public void testSeckillLogic() throws Exception {
		long id = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()){
			Logger.info("exposer={}",exposer);
			long phone = 131414223L;
			String md5 = "4d37f1eec4119631e5b8366ae1162274";
			SeckillExecution execution;
			try {
				execution = seckillService.executeSeckill(id, phone, md5);
				Logger.info("result={}",execution);
			} catch (SeckillCloseException e) {
				Logger.error(e.getMessage());
			} catch (RepeatKillExecption e) {
				Logger.error(e.getMessage());
			} catch (Exception e) {
				Logger.error(e.getMessage());
			}
		} else {
			//秒杀未开启
			Logger.warn("exposer={}",exposer);
		}
	}

}
