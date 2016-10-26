package org.spring.casapp;

public class NullSessionException extends Exception {
	public NullSessionException() {
		super("获取系统session对象异常。");
	}
}
