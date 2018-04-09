package org.wl.user.service;

import org.springframework.stereotype.Service;
import org.wl.base.service.BaseMapper;
import org.wl.base.service.BaseService;
import org.wl.user.entity.UserInfo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoService extends BaseService implements UserInfoMapper{

    @Resource
    private UserInfoMapper userInfoMapper;

    public <T> BaseMapper getMapper() {
        return userInfoMapper;
    }

    public List<UserInfo> selectAll() {
        return userInfoMapper.selectAll();
    }
}
