package my.yrzy.user.service;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import my.yrzy.common.common.PageInfo;
import my.yrzy.common.common.Paging;
import my.yrzy.common.exception.ServiceException;
import my.yrzy.user.dao.UserDao;
import my.yrzy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangzefeng on 14/12/14
 */
@Service @Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Long create(User user) {
        if(user == null) {
            log.error("user is null when create user");
            throw new ServiceException("create.user.param.illegal");
        }
        User exist = userDao.findByName(user.getRealName());
        if(exist != null) {
            log.warn("user name {} already exist", user.getRealName());
            throw new ServiceException("user.name.duplicate");
        }
        exist = userDao.findByPhone(user.getPhone());
        if(exist != null) {
            log.warn("user phone {} already exist", user.getPhone());
            throw new ServiceException("user.phone.duplicate");
        }
        userDao.create(user);
        return user.getId();
    }

    @Override
    public Boolean update(User user, User operator) {
        if(user == null || user.getId() == null) {
            log.error("user or user id is null when update user");
            throw new ServiceException("update.user.param.illegal");
        }
        return userDao.update(user);
    }

    @Override
    public Boolean delete(Long id, User operator) {
        if(id == null) {
            log.error("user id is null when delete user");
            throw new ServiceException("delete.user.param.illegal");
        }
        return userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        if(id == null) {
            log.error("user id is null when find user by id");
            throw new ServiceException("query.user.param.illegal");
        }
        return userDao.findById(id);
    }

    @Override
    public User findBy(User.LoginType loginType, String userName) {
        if(loginType == null || userName == null) {
            log.error("login type or user name is null when find user");
            throw new ServiceException("query.user.param.illegal");
        }
        return userDao.findBy(loginType, userName);
    }

    @Override
    public Paging<User> paging(Integer pageNo, Integer size, String userName, Long userId, String phone) {
        Map<String, Object> params = composeUserQueryMap(pageNo, size, userName, userId, phone);

        return userDao.paging(params);
    }

    private Map<String, Object> composeUserQueryMap(Integer pageNo, Integer size, String userName, Long userId, String phone) {
        Map<String, Object> params = PageInfo.of(pageNo, size).toMap();

        if(!Strings.isNullOrEmpty(userName)) {
            params.put("nickName", userName);
        }

        if(userId != null) {
            params.put("id", userId);
        }

        if(!Strings.isNullOrEmpty(phone)) {
            params.put("phone", phone);
        }

        return params;
    }
}
