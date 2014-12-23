package my.yrzy.trade.dao;

import com.google.common.base.Objects;
import my.yrzy.common.common.Paging;
import my.yrzy.trade.model.Order;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzefeng on 14/12/7
 */
@Repository
public class OrderDao extends SqlSessionDaoSupport {

    public void create(Order order) {
        getSqlSession().insert("Order.create", order);
    }

    public Boolean delete(Long id) {
        return getSqlSession().delete("Order.delete", id) == 1;
    }

    public Order findById(Long id) {
        return getSqlSession().selectOne("Order.findById", id);
    }

    public Paging<Order> paging(Map<String, Object> params) {
        Long total = getSqlSession().selectOne("Order.count", params);

        if(Objects.equal(total, 0L)) {
            return Paging.empty();
        }

        List<Order> data = getSqlSession().selectList("Order.find", params);

        return new Paging<Order>(total, data);
    }
}
