/**
 * 
 */
package com.haih.service;

import java.util.List;

import com.haih.dto.Exposer;
import com.haih.dto.SeckillExecution;
import com.haih.entity.Seckill;
import com.haih.exception.RepeatKillExecption;
import com.haih.exception.SeckillCloseException;



/**
 * @author haih
 * 业务接口：站在“使用者”角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型／异常）
 * 
 */
public interface SeckillSerivce {

	/**
	 * 查询所有秒杀记录
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀记录
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 秒杀开启时输出秒杀接口地址，
	 * 否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
	 throws SecurityException, RepeatKillExecption,SeckillCloseException;
	
}
