package my.yrzy.shop.service;

import my.yrzy.common.common.Paging;
import my.yrzy.shop.model.Shop;
import my.yrzy.user.model.User;

/**
 * Created by yangzefeng on 14/12/14
 */
public interface ShopService {

    /**
     * 创建店铺
     * @param shop 待创建的店铺
     * @return 创建的店铺id
     */
    public Long create(Shop shop);

    /**
     * 更新店铺
     * @param shop 待更新的店铺
     * @return 是否更新成功
     */
    public Boolean update(Shop shop, User operator);

    /**
     * 删除店铺
     * @param id 店铺id
     * @return 是否删除成功
     */
    public Boolean delete(Long id, User operator);

    /**
     * 根据id查询店铺
     * @param id 店铺id
     * @return 店铺
     */
    public Shop findById(Long id);

    /**
     * 分页显示店铺列表
     * @param pageNo 页码
     * @param size 每页大小
     * @param shopName 店铺名称
     * @return 分页后的店铺列表
     */
    public Paging<Shop> paging(Integer pageNo, Integer size, String shopName);
}
