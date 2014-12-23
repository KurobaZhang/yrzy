package my.yrzy.shop.dao;

import com.google.common.base.Objects;
import my.yrzy.common.common.Paging;
import my.yrzy.shop.model.Shop;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzefeng on 14/12/7
 */
@Repository
public class ShopDao extends SqlSessionDaoSupport {

    public void create(Shop shop) {
        getSqlSession().insert("Shop.create", shop);
    }

    public Boolean update(Shop shop) {
        return getSqlSession().update("Shop.update", shop) == 1;
    }

    public Boolean delete(Long id) {
        return getSqlSession().delete("Shop.delete", id) == 1;
    }

    public Shop findById(Long id) {
        return getSqlSession().selectOne("Shop.findById", id);
    }

    public Shop findByUserId(Long userId) {
        return getSqlSession().selectOne("Shop.findByUserId", userId);
    }

    public Paging<Shop> paging(Map<String, Object> params) {
        Long total = getSqlSession().selectOne("Shop.count", params);

        if(Objects.equal(total, 0L)) {
            return Paging.empty();
        }

        List<Shop> data = getSqlSession().selectList("Shop.find", params);

        return new Paging<Shop>(total, data);
    }
}
