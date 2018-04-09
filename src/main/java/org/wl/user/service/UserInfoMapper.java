package org.wl.user.service;

import org.wl.base.service.BaseMapper;
import org.wl.user.entity.UserInfo;

import java.util.List;

 /**
  * @className UserInfoMapper
  * @author wangLei
  * @date 2018/3/23 17:55
  * @version v1.0
  * @description
  */
public interface UserInfoMapper extends BaseMapper{
    /**
     * 查询全部
     * @author wangLei
     * @date 2018/3/23 17:54
     * @param
     * @return
     */
    List<UserInfo> selectAll();
}
