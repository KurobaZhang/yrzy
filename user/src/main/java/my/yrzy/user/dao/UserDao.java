package my.yrzy.user.dao;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import my.yrzy.common.common.Paging;
import my.yrzy.user.model.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzefeng on 14/12/6
 */
@Repository @Slf4j
public class UserDao extends SqlSessionDaoSupport {

    public void create(User user) {
        getSqlSession().insert("User.create", user);
    }

    public Boolean update(User user) {
        return getSqlSession().update("User.update", user) == 1;
    }

    public Boolean delete(Long id) {
        return getSqlSession().delete("User.delete", id) == 1;
    }

    public User findById(Long id) {
        return getSqlSession().selectOne("User.findById", id);
    }

    public User findBy(User.LoginType loginType, String loginName) {
        switch (loginType) {
            case NAME:
                return getSqlSession().selectOne("User.findByName", loginName);
            case MOBILE:
                return getSqlSession().selectOne("User.findByMobile", loginName);
            default:
                log.error("unknown login type {}", loginType);
                throw new IllegalArgumentException("unknown.login.type");
        }
    }

    public User findByName(String realName) {
        return getSqlSession().selectOne("User.findByName", realName);
    }

    public User findByPhone(String phone) {
        return getSqlSession().selectOne("User.findByPhone", phone);
    }

    public Paging<User> paging(Map<String, Object> params) {
        Long total = getSqlSession().selectOne("User.count", params);
        if(Objects.equal(total, 0L)) {
            return Paging.empty();
        }
        List<User> data = getSqlSession().selectList("User.find", params);
        return new Paging<User>(total, data);
    }
}
