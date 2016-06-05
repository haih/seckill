/**
 * 
 */
package com.haih.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.haih.dao.SeckillDao;
import com.haih.dao.SuccessKilledDao;
import com.haih.dto.Exposer;
import com.haih.dto.SeckillExecution;
import com.haih.entity.Seckill;
import com.haih.entity.SuccessKilled;
import com.haih.enums.SeckillStateEnum;
import com.haih.exception.RepeatKillExecption;
import com.haih.exception.SeckillCloseException;
import com.haih.exception.SeckillException;
import com.haih.service.SeckillSerivce;

/**
 * @author haih
 *
 */
//@component
@Service
public class SeckillServiceImpl implements SeckillSerivce {

	private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

	//注入Service依赖
	@Autowired  //@Resource, @inject
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	// md5盐值，用于混淆md5
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
		if (null == seckill) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();

		Date nowTime = new Date();

		if (nowTime.getTime() < startTime.getTime()
				|| nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, startTime.getTime(),
					nowTime.getTime(), endTime.getTime());
		}
		// 转化特定字符串的过程，不可逆
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	/**
	 * 使用注解控制事务方法的优点：
	 * 1: 开发团队打成一致约定，明确标注事务方法的编程风格
	 * 2: 保证事务方法的执行时间尽可能短，不要穿插其他网络操作rpc/http请求或者剥离到事务方法外部
	 * 3: 不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone,String md5) 
			throws SeckillException, RepeatKillExecption,SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill date rewrite!");
		}

		Date nowTime = new Date();
		try {
			// 执行秒杀逻辑；减库存 ＋ 记录购买行为
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if (updateCount <= 0) {
				// 没有更新到记录,秒杀结束
				throw new SeckillCloseException("seckill is closed!");
			} else {
				// 记录购买行为
				int insertCount = successKilledDao.insertSuccessKilled(
						seckillId, userPhone);
				// 唯一：seckillId , userPhone
				if (insertCount <= 0) {
					// 重复秒杀
					throw new RepeatKillExecption("seckill repeated!");
				} else {
					// 秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillExecption e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			//所有编译期异常，转化为运行期异常
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
	}

}
