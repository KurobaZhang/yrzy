package my.yrzy.user.service;

import my.yrzy.common.common.Paging;
import my.yrzy.user.model.User;

/**
 * Created by yangzefeng on 14/12/14
 */
public interface UserService {

    /**
     * 创建用户
     * @param user 用户
     * @return 创建后的用户id
     */
    public Long create(User user);


    /**
     * 更新用户
     * @param user 待更新用户
     * @return 是否更新成功
     */
    public Boolean update(User user, User operator);

    /**
     * 删除用户
     * @param id 用户id
     * @return 是否删除成功
     */
    public Boolean delete(Long id, User operator);

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    public User findById(Long id);

    /**
     * 登录是的用户查询
     * @param loginType 登录方式，分手机，昵称登录
     * @param userName 对应的用户名
     * @return 用户
     */
    public User findBy(User.LoginType loginType, String userName);

    /**
     * 分页查询用户
     * @param pageNo 页码
     * @param size 每页数量
     * @param userName 用户名
     * @param userId 用户id
     * @param phone 用户手机号
     * @return 分页后的用户列表
     */
    public Paging<User> paging(Integer pageNo, Integer size, String userName, Long userId, String phone);
}
