package my.yrzy.trade.service;

import my.yrzy.common.common.Paging;
import my.yrzy.trade.model.Order;
import my.yrzy.user.model.User;

/**
 * Created by yangzefeng on 14/12/14
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order 待创建订单
     * @return 创建的订单id
     */
    public Long create(Order order);

    /**
     * 删除订单
     * @param id 订单id
     * @param operator 当前操作用户
     * @return 操作是否成功
     */
    public Boolean delete(Long id, User operator);

    /**
     * 根据id查找订单
     * @param id 订单id
     * @return 订单
     */
    public Order findById(Long id);

    /**
     * 买家订单中心列表
     * @param pageNo 页码
     * @param size 每页大小
     * @param orderId 订单id
     * @param currentUser 当前登录用户
     * @return 分页的订单列表
     */
    public Paging<Order> buyerPaging(Integer pageNo, Integer size, Long orderId, User currentUser);

    /**
     * 卖家订单中心列表
     * @param pageNo 页码
     * @param size 每页大小
     * @param orderId 订单id
     * @param currentUser 当前登录用户
     * @return 分页的订单列表
     */
    public Paging<Order> sellerPaging(Integer pageNo, Integer size, Long orderId, User currentUser);

    /**
     * 运营订单中心列表
     * @param pageNo 页码
     * @param size 每页大小
     * @param orderId 订单id
     * @return 分页的订单列表
     */
    public Paging<Order> adminPaging(Integer pageNo, Integer size, Long orderId);
}
