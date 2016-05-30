/**
 * 
 */
package com.haih.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import com.haih.dao.SeckillDao;
import com.haih.dao.SuccessKilledDao;
import com.haih.dto.Exposer;
import com.haih.dto.SeckillExecution;
import com.haih.entity.Seckill;
import com.haih.entity.SuccessKilled;
import com.haih.exception.RepeatKillExecption;
import com.haih.exception.SeckillCloseException;
import com.haih.exception.SeckillException;
import com.haih.service.SeckillSerivce;
import com.mysql.fabric.xmlrpc.base.Data;

/**
 * @author haih
 *
 */
public class SeckillServiceImpl implements SeckillSerivce{

	private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
	
	private SeckillDao seckillDao;
	
	private SuccessKilledDao successKilledDao;
	
	//md5盐值，用于混淆md5
	private final String salt = "qwertyuiopasdfghjklzxcvbnm,./";
	
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		// TODO Auto-generated method stub
		return seckillDao.queryById(seckillId);
	} 

	public Exposer exportSeckillUrl(long seckillId) {
	    Seckill seckill = seckillDao.queryById(seckillId);
	    if(null == seckill){
	    	return new Exposer(false, seckillId);
	    }
	    Date startTime = seckill.getStartTime();
	    Date endTime = seckill.getEndTime();
	    
	    Date nowTime = new Date();
	    
	    if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
	    	return new Exposer(false, seckillId,startTime.getTime(),nowTime.getTime(),endTime.getTime());
	    }
	    //转化特定字符串的过程，不可逆
	    String md5 = getMD5(seckillId);  
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId){
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}	 
	
	public SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
			 throws SeckillException, RepeatKillExecption,SeckillCloseException {
		if(md5 == null || md5.equals(getMD5(seckillId))){
			throw new SeckillException("seckill date rewrite!");
		}
		
		Date nowTime = new Date();
		//执行秒杀逻辑；减库存 ＋ 记录购买行为
		int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
		if(updateCount <= 0){
			//没有更新到记录,秒杀结束
			throw new SeckillCloseException("seckill is closed!");
		} else {
			//记录购买行为
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			//唯一：seckillId , userPhone
			if(insertCount <= 0){
				throw new RepeatKillExecption("seckill repeated!");
			} else {
				SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
				return new SeckillExecution(seckillId, 1, "秒杀成功", successKilled);
			}
		}
		return new SeckillExecution;
	}
	
}
