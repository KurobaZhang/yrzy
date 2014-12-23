package my.yrzy.trade.service;

import lombok.extern.slf4j.Slf4j;
import my.yrzy.common.common.PageInfo;
import my.yrzy.common.common.Paging;
import my.yrzy.common.exception.ServiceException;
import my.yrzy.trade.dao.OrderDao;
import my.yrzy.trade.model.Order;
import my.yrzy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangzefeng on 14/12/14
 */
@Service @Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public Long create(Order order) {
        if(order == null) {
            log.error("order is null when create order");
            throw new ServiceException("order.create.param.illegal");
        }

        orderDao.create(order);
        return order.getId();
    }

    @Override
    public Boolean delete(Long id, User operator) {
        if(id == null) {
            log.error("order id is null when delete order");
            throw new ServiceException("order.delete.param.illegal");
        }

        return orderDao.delete(id);
    }

    @Override
    public Order findById(Long id) {
        if(id == null) {
            log.error("order id is null when find by id");
            throw new ServiceException("order.query.param.illegal");
        }

        return orderDao.findById(id);
    }

    @Override
    public Paging<Order> buyerPaging(Integer pageNo, Integer size, Long orderId, User currentUser) {
        Map<String, Object> params = composeParams(pageNo, size, orderId, currentUser.getId(), null);

        return orderDao.paging(params);
    }

    @Override
    public Paging<Order> sellerPaging(Integer pageNo, Integer size, Long orderId, User currentUser) {
        Map<String, Object> params = composeParams(pageNo, size, orderId, null, currentUser.getId());

        return orderDao.paging(params);
    }

    @Override
    public Paging<Order> adminPaging(Integer pageNo, Integer size, Long orderId) {
        Map<String, Object> params = composeParams(pageNo, size, orderId, null, null);

        return orderDao.paging(params);
    }

    private Map<String, Object> composeParams(Integer pageNo, Integer size, Long orderId, Long buyerId, Long sellerId) {
        Map<String, Object> params = PageInfo.of(pageNo, size).toMap();

        if(orderId != null) {
            params.put("id", orderId);
        }

        if(buyerId != null) {
            params.put("buyerId", buyerId);
        }

        if(sellerId != null) {
            params.put("sellerId", sellerId);
        }

        return params;
    }
}
