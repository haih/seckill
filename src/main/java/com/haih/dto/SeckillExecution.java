/**
 * 
 */
package com.haih.dto;

import com.haih.entity.SuccessKilled;
import com.haih.enums.SeckillStateEnum;

/**
 * @author haih
 * 封装秒杀执行后结果
 */
public class SeckillExecution {

	private long seckillId;
	
	//执行结果状态
	private int state;

	//状态描述信息
	private String stateInfo;
	
	//秒杀成功对象
	private SuccessKilled successKilled;
	
	public SeckillExecution(long seckillId,SeckillStateEnum stateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
}

