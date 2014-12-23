package my.yrzy.business.service;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import my.yrzy.business.dao.BusinessDao;
import my.yrzy.business.model.Business;
import my.yrzy.common.common.PageInfo;
import my.yrzy.common.common.Paging;
import my.yrzy.common.exception.ServiceException;
import my.yrzy.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangzefeng on 14/12/14
 */
@Service @Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    public Long create(Business business) {
        if(business == null || business.getId() == null) {
            log.error("business is null when create business");
            throw new ServiceException("create.business.param.illegal");
        }

        businessDao.create(business);
        return business.getId();
    }

    @Override
    public Boolean update(Business business, User operator) {
        if(business == null || business.getId() == null) {
            log.error("business or business id is null when update business");
            throw new ServiceException("update.business.param.illegal");
        }

        return businessDao.update(business);
    }

    @Override
    public Boolean delete(Long id, User operator) {
        if(id == null) {
            log.error("business id is null when delete business");
            throw new ServiceException("delete.business.param.illegal");
        }

        return businessDao.delete(id);
    }

    @Override
    public Business findById(Long id) {
        if(id == null) {
            log.error("business id is null when find business by id");
            throw new ServiceException("query.business.param.illegal");
        }

        return businessDao.findById(id);
    }

    @Override
    public Paging<Business> paging(Integer pageNo, Integer size, Long businessId,
                                   String businessName, String shopName, String userName) {
        Map<String, Object> params = composeParams(pageNo, size, businessId, businessName, shopName, userName);

        return businessDao.paging(params);
    }

    private Map<String, Object> composeParams(Integer pageNo, Integer size, Long businessId,
                                              String businessName, String shopName, String userName) {
        Map<String, Object> params = PageInfo.of(pageNo, size).toMap();

        if(!Strings.isNullOrEmpty(shopName)) {
            params.put("shopName", shopName);
        }

        if(!Strings.isNullOrEmpty(userName)) {
            params.put("userName", userName);
        }

        if(!Strings.isNullOrEmpty(businessName)) {
            params.put("name", businessName);
        }

        if(businessId != null) {
            params.put("id", businessId);
        }

        return params;
    }
}
