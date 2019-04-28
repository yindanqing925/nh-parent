package org.nh.springboot.user.service.impl;

import org.nh.springboot.user.dao.UserDao;
import org.nh.springboot.user.model.User;
import org.nh.springboot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userInfoDao;

    @Override
    public User getUserInfoById(Integer id) {
        return userInfoDao.selectByPrimaryKey(id);
    }
}
