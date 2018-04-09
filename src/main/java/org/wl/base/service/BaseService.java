package org.wl.base.service;

public abstract class BaseService {

	public abstract <T> BaseMapper getMapper();

	/*
	 * (non-Javadoc)
	 * @see com.accurad.amol.base.service.BaseService#deleteByPrimaryKey(java.lang.Integer)
	 */
	public int deleteByPrimaryKey(Integer pk) {
		return getMapper().deleteByPrimaryKey(pk);
	}

	/*
	 * (non-Javadoc)
	 * @see com.accurad.amol.base.service.BaseService#insert(java.lang.Object)
	 */
	public int insert(Object obj) {
		return getMapper().insert(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see com.accurad.amol.base.service.BaseService#insertSelective(java.lang.Object )
	 */
	public int insertSelective(Object obj) {
		return getMapper().insertSelective(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see com.accurad.amol.base.service.BaseService#selectByPrimaryKey(java.lang .Integer)
	 */
	public Object selectByPrimaryKey(Integer pk) {
		return getMapper().selectByPrimaryKey(pk);
	}

	/*
	 * (non-Javadoc)
	 * @see com.accurad.amol.base.service.BaseService#updateByPrimaryKeySelective (java.lang.Object)
	 */
	public int updateByPrimaryKeySelective(Object obj) {
		return getMapper().updateByPrimaryKeySelective(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see com.accurad.amol.base.service.BaseService#updateByPrimaryKeyWithBLOBs (java.lang.Object)
	 */
	public int updateByPrimaryKeyWithBLOBs(Object obj) {
		return getMapper().updateByPrimaryKeyWithBLOBs(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see com.accurad.amol.base.service.BaseService#updateByPrimaryKey(java.lang .Object)
	 */
	public int updateByPrimaryKey(Object obj) {
		return getMapper().updateByPrimaryKey(obj);
	}

}
