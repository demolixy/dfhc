/**
 * 
 */
package com.dfhc;

/**
 * 工程运行期异常
 * @author HC_WX
 */
public class PjException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5911940609720822853L;
	public PjException(){
		super();
	}
	public PjException(String msg){
		super(msg);
	}
	public PjException(String msg, Throwable e){
		super(msg,e);
	}
	public PjException(Throwable e){
		super(e);
	}
}
