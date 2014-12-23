package my.yrzy.shop.service;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import my.yrzy.common.common.PageInfo;
import my.yrzy.common.common.Paging;
import my.yrzy.common.exception.ServiceException;
import my.yrzy.shop.dao.ShopDao;
import my.yrzy.shop.model.Shop;
import my.yrzy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangzefeng on 14/12/14
 */
@Service @Slf4j
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;


    @Override
    public Long create(Shop shop) {
        if(shop == null) {
            log.error("shop is null when create shop");
            throw new ServiceException("create.shop.param.illegal");
        }

        shopDao.create(shop);
        return shop.getId();
    }

    @Override
    public Boolean update(Shop shop, User operator) {
        if(shop == null || shop.getId() == null) {
            log.error("shop or shop id is null when update shop");
            throw new ServiceException("update.shop.param.illegal");
        }

        return shopDao.update(shop);
    }

    @Override
    public Boolean delete(Long id, User operator) {
        if(id == null) {
            log.error("shop id is null when delete shop");
            throw new ServiceException("delete.shop.param.illegal");
        }

        return shopDao.delete(id);
    }

    @Override
    public Shop findById(Long id) {
        if(id == null) {
            log.error("shop id is null when find shop by id");
            throw new ServiceException("query.shop.param.illegal");
        }

        return shopDao.findById(id);
    }

    @Override
    public Paging<Shop> paging(Integer pageNo, Integer size, String shopName) {
        Map<String, Object> params = composeParams(pageNo, size, shopName);

        return shopDao.paging(params);
    }

    private Map<String, Object> composeParams(Integer pageNo, Integer size, String shopName) {
        Map<String, Object> params = PageInfo.of(pageNo, size).toMap();

        if(!Strings.isNullOrEmpty(shopName)) {
            params.put("shopName", shopName);
        }

        return params;
    }
}
