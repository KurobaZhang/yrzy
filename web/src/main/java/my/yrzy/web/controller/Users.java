package my.yrzy.web.controller;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import my.yrzy.common.common.CommonConstants;
import my.yrzy.common.exception.JsonResponseException;
import my.yrzy.common.exception.ServiceException;
import my.yrzy.user.model.User;
import my.yrzy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangzefeng on 14/12/15
 */
@Controller
@RequestMapping("/api/users")
@Slf4j
public class Users {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long signup(@RequestParam(value = "nickName", required = false) String nickName, @RequestParam("password") String password,
                       @RequestParam(value = "realName") String realName,
                       @RequestParam("phone") String phone) {

        try {
            User user = new User();
            user.setNickName(nickName);
            user.setRealName(realName);
            user.setPassword(password);
            user.setPhone(phone);
            return userService.create(user);
        }catch (ServiceException se) {
            throw new JsonResponseException(se.getMessage());
        }catch (Exception e) {
            log.error("fail to sign up user, cause:{}", e);
            throw new JsonResponseException("user.create.fail");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void login(@RequestParam("loginBy") String loginBy, @RequestParam("password") String password,
                      @RequestParam(value = "type", defaultValue = "1") Integer type,
                      HttpServletRequest request) {
        User.LoginType loginType = User.LoginType.from(type);
        if(loginType == null) {
            log.error("unknown login type {}", type);
            throw new JsonResponseException("unknown.login.type");
        }
        User user = userService.findBy(loginType, loginBy);
        if(!Objects.equal(user.getPassword(), password)) {
            log.error("password incorrect");
            throw new JsonResponseException("user.password.incorrect");
        }

        request.getSession().setAttribute(CommonConstants.SESSION_USER_ID, user.getId());
    }
}
