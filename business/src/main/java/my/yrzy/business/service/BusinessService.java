package my.yrzy.business.service;

import my.yrzy.business.model.Business;
import my.yrzy.common.common.Paging;
import my.yrzy.user.model.User;

/**
 * Created by yangzefeng on 14/12/14
 */
public interface BusinessService {

    /**
     * 创建生意
     * @param business 待创建的生意
     * @return 创建后的生意id
     */
    public Long create(Business business);

    /**
     * 更新生意内容
     * @param business 待更新的生意
     * @return 是否更新成功
     */
    public Boolean update(Business business, User operator);

    /**
     * 删除生意
     * @param id 生意id
     * @return 是否删除成功
     */
    public Boolean delete(Long id, User operator);

    /**
     * 根据id查询生意
     * @param id 生意id
     * @return 生意
     */
    public Business findById(Long id);

    /**
     * 分页查询生意
     * @param pageNo 页码
     * @param size 每页大小
     * @param businessId 生意id
     * @param businessName 生意名称
     * @param shopName 生意所属店铺名称
     * @param userName 生意所属商家名称
     * @return 分页后的生意列表
     */
    public Paging<Business> paging(Integer pageNo, Integer size, Long businessId, String businessName,
                                   String shopName, String userName);
}
