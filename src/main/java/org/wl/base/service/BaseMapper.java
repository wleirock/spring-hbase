package org.wl.base.service;


public interface BaseMapper {
	int deleteByPrimaryKey(Integer pk);

	int insert(Object obj);

	int insertSelective(Object obj);

	Object selectByPrimaryKey(Integer pk);

	int updateByPrimaryKeySelective(Object obj);

	int updateByPrimaryKeyWithBLOBs(Object obj);

	int updateByPrimaryKey(Object obj);

}
