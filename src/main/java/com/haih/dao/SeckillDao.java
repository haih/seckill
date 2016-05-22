/**
 * 
 */
package com.haih.dao;

import java.sql.Date;
import java.util.List;
import com.haih.entity.Seckill;

/**
 * @author haih
 *
 */
public interface SeckillDao {

	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return 如果影响行数 > 1，表示更新的记录行数
	 */
	int reduceNumber(long seckillId, Date killTime);
	
	/*
	 * 根据id查询秒杀对象
	 */
	Seckill queryById(long seckillId);
	
	/*
	 * 根据偏移量查询秒杀商品列表
	 */
	List<Seckill> queryAll(int offset, int limit);
}
