/**
 * 
 */
package com.haih.dto;

/**
 * 暴露秒杀地址dto
 * @author haih
 *
 */
public class Exposer {

	/**
	 * 是否开启秒杀
	 */
	private boolean exposed;
	
	/**
	 * 一种加密措施
	 */
	private String md5;
	
	/*
	 * id
	 */
	private long seckillId;
	
	/**
	 * 秒杀开始时间
	 */
	private long start;
	
	/**
	 * 系统当前时间
	 */
	private long now;
	
	/**
	 * 秒杀结束 时间
	 */
	private long end;
	
	public Exposer(boolean exposed,long seckillId, long start, long now, long end) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.start = start;
		this.now = now;
		this.end = end;
	}

	public Exposer(boolean exposed,String md5, long seckillId){
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;		
	}
	
	public Exposer(boolean exposed, long seckillId){
		this.exposed = exposed;
		this.seckillId = seckillId;		
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", seckillId="
				+ seckillId + ", start=" + start + ", now=" + now + ", end="
				+ end + "]";
	}
}
