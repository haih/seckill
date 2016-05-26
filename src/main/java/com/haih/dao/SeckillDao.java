/**
 * 
 */
package com.haih.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	/*
	 * 根据id查询秒杀对象
	 */
	Seckill queryById(@Param("seckillId") long seckillId);
	
	/*
	 * 根据偏移量查询秒杀商品列表
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
