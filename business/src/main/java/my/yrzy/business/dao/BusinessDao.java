package my.yrzy.business.dao;

import com.google.common.base.Objects;
import my.yrzy.business.model.Business;
import my.yrzy.common.common.Paging;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzefeng on 14/12/7
 */
@Repository
public class BusinessDao extends SqlSessionDaoSupport {

    public void create(Business business) {
        getSqlSession().insert("Business.create", business);
    }

    public Boolean update(Business business) {
        return getSqlSession().update("Business.update", business) == 1;
    }

    public Boolean delete(Long id) {
        return getSqlSession().delete("Business.delete", id) == 1;
    }

    public Business findById(Long id) {
        return getSqlSession().selectOne("Business.findById", id);
    }

    public List<Business> findByUserId(Long userId) {
        return getSqlSession().selectList("Business.findByUserId", userId);
    }

    public Paging<Business> paging(Map<String, Object> params) {
        Long total = getSqlSession().selectOne("Business.count", params);

        if(Objects.equal(total, 0L)) {
            return Paging.empty();
        }

        List<Business> data = getSqlSession().selectList("Business.find", params);

        return new Paging<Business>(total, data);
    }
}
