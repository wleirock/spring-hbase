package org.wl.base.entity;

import java.io.Serializable;

/**
 * @className: ResponseEntity.java
 * @author: wanglei
 * @version: v1.0
 * @date: 2018年3月20日 下午12:05:05
 * @description: 接口返回数据封装
 */
public class ResponseEntity implements Serializable {

	public ResponseEntity() {
	}

	public ResponseEntity(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public ResponseEntity(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	private static final long serialVersionUID = -5866254830646678795L;

	/**
	 * http状态码
	 */
	private String status;

	/**
	 * 消息
	 */
	private String message;

	/**
	 * 返回的数据
	 */
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
