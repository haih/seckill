/**
 * 
 */
package com.haih.exception;

/**
 * @author haih
 * 重复秒杀异常（运行期异常）
 */
public class RepeatKillExecption extends SeckillException {

	public RepeatKillExecption(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RepeatKillExecption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
