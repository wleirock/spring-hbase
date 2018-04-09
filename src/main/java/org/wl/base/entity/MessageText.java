package org.wl.base.entity;

public enum MessageText {

	QUERY_SUCCES("查询成功"), QUERT_FAILURE("查询失败"), INSERT_SCUUESS("保存成功"), INSERT_FAILURE("保存失败"),
	UNSUPPORTED_MEDIA_TYPE("不支持当前媒体类型"), BAD_REQUEST("解析参数失败"), INTERNAL_SERVER_ERROR("服务运行异常"),
	METHOD_NOT_ALLOWED("请求方式不支持"), NOT_FOUND("请求地址错误"), SAVE_SUCCESS("保存成功");

	private String text;

	private MessageText(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
