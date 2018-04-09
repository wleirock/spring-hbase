package org.wl.user.entity;

import org.wl.hbase.annotation.HbaseColumn;
import org.wl.hbase.annotation.HbaseTable;

@HbaseTable(tableName = "PATIENT_INFO", columnFamilyName = "CF")
public class PatientInfo {

	@HbaseColumn(columnName = "ID", isRowKey = true, familyName = "CF")
	private String id;

	@HbaseColumn(columnName = "NAME", familyName = "CF")
	private String name;

	@HbaseColumn(columnName = "SEX", familyName = "CF")
	private String sex;

	@HbaseColumn(columnName = "USER_ID", familyName = "CF")
	private String userId;

	@HbaseColumn(columnName = "CHECK_NO", familyName = "CF")
	private String checkNo;

	@HbaseColumn(columnName = "CHECK_DATE", familyName = "CF")
	private String checkDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
}
