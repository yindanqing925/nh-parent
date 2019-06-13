package org.nh.springboot.user.service;

import org.nh.springboot.user.model.User;

import java.util.Collection;

public interface UserService {

    User getUserInfoById(Integer id);

    int modifyUser(User user);

    /**
     * 1234512312
     */
    int insertbatch(Collection<User> users);

}
