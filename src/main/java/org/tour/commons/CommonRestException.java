package org.tour.commons;

/**
 * 공통 Rest 예외 처리 
 * @author LEE YONGGYO
 *
 */
public class CommonRestException extends RuntimeException { 
	public CommonRestException(String message) {
		super(message);
	}
}