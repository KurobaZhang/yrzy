package my.yrzy.web.common.interceptors;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import my.yrzy.common.common.CommonConstants;
import my.yrzy.common.util.BaseUser;
import my.yrzy.common.util.UserUtil;
import my.yrzy.user.model.User;
import my.yrzy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yangzefeng on 14/12/18
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession(false);

        if(session != null) {
            Object userId = session.getAttribute(CommonConstants.SESSION_USER_ID);

            if(userId != null) {
                try {
                    User user = userService.findById(Long.valueOf(userId.toString()));

                    if(user != null) {
                        UserUtil.putUser(makeBaseUser(user));
                        return true;
                    }
                    return false;
                }catch (Exception e) {
                    log.error("fail to find user by id {}, cause:{}", userId, Throwables.getStackTraceAsString(e));
                    return false;
                }
            }
        }

        return true;
    }

    private BaseUser makeBaseUser(User user) {
        BaseUser baseUser = new BaseUser();

        baseUser.setId(user.getId());
        baseUser.setRealName(user.getRealName());
        baseUser.setType(user.getType());
        baseUser.setStatus(user.getStatus());

        return baseUser;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        UserUtil.cleanCurrentUser();
    }
}
