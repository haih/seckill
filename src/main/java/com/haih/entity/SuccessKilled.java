/**
 * 
 */
package com.haih.entity;

import java.sql.Date;

/**
 * @author haih
 *
 */
public class SuccessKilled {

	private long seckllId;
	
	private long userPhone;
	
	private short state;
	
	private Date createTime;

	private Seckill seckill;
	
	public long getSeckllId() {
		return seckllId;
	}

	public void setSeckllId(long seckllId) {
		this.seckllId = seckllId;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@Override
	public String toString() {
		return "SuccessKilled [seckllId=" + seckllId + ", userPhone="
				+ userPhone + ", state=" + state + ", createTime=" + createTime
				+ "]";
	}
	
	
}
