package org.nh.springboot.user.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.nh.common.cache.Cache;
import org.nh.common.cache.CachekeyPrefix;
import org.nh.springboot.user.dao.UserDao;
import org.nh.springboot.user.model.User;
import org.nh.springboot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userInfoDao;

    @Autowired
    private Cache cache;

    @Override
    public User getUserInfoById(Integer id) {
        /*String idStr = ObjectUtils.toString(id);
        if(StringUtils.isBlank(idStr)){
            return null;
        }
        String userStr;
        if(StringUtils.isBlank(userStr = cache.hget(CachekeyPrefix.USER_BEAN, idStr))){
            User user = userInfoDao.selectByPrimaryKey(id);
            cache.hset(CachekeyPrefix.USER_BEAN, idStr, JSON.toJSONString(user));
            return user;
        }
        return JSON.parseObject(userStr, User.class);*/
        User user = userInfoDao.selectByPrimaryKey(id);
        System.out.println(JSON.toJSONString(user));
        return user;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.READ_COMMITTED)
    public int modifyUser(User user) {
        return userInfoDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public int insertbatch(Collection<User> users) {
        return userInfoDao.insertbatch(users);
    }
}
