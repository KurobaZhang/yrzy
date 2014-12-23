package my.yrzy.web.common.interceptors;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.net.HttpHeaders;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import my.yrzy.common.exception.JsonResponseException;
import my.yrzy.common.util.BaseUser;
import my.yrzy.common.util.UserUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by yangzefeng on 14/12/18
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static Set<WhiteItem> whiteList;

    private static Set<AuthItem> protectedList;

    @Value("#{app.mainSite}")
    private String mainSite;

    @PostConstruct
    public void init() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI().substring(request.getContextPath().length());
        String method = request.getMethod().toLowerCase();

        BaseUser currentUser = UserUtil.getUser();

        for(WhiteItem wi : whiteList) {
            if(methodMatch(wi.httpMethods, method) && URIMatch(wi.pattern, requestURI)) {
                return true;
            }
        }

        for(AuthItem ai : protectedList) {
            if(URIMatch(ai.pattern, requestURI)) {
                if(currentUser != null) {
                    if (typeMatch(ai.types, currentUser)) {
                        return true;
                    }

                    throw new JsonResponseException(401, "authorize.fail");
                }else {
                    log.info("user not login");
                    redirectToLogin(request, response);
                    return false;
                }
            }
        }

        if(!Objects.equal(method, "get") && currentUser == null) {
            log.warn("write operation must login");
            redirectToLogin(request, response);
            return false;
        }

        return true;
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(isAjaxRequest(request)) {
            throw new JsonResponseException(401, "authorize.fail");
        }

        String url = request.getRequestURL().toString();

        if(!Strings.isNullOrEmpty(request.getQueryString())) {
            url = url + "?" + request.getQueryString();
        }

        UriComponents uriComponents =
                UriComponentsBuilder.fromUriString(mainSite+ "?target={target}").build();
        URI uri = uriComponents.expand(url).encode().toUri();

        response.sendRedirect(uri.toString());
    }

    private Boolean isAjaxRequest(HttpServletRequest request) {
        return Objects.equal(request.getHeader(HttpHeaders.X_REQUESTED_WITH), "XMLHttpRequest");
    }

    private boolean typeMatch(Set<Integer> types, BaseUser currentUser) {
        return types.contains(currentUser.getType());
    }

    private boolean URIMatch(Pattern pattern, String requestURI) {
        return pattern.matcher(requestURI).find();
    }

    private boolean methodMatch(Set<String> methods, String requestMethod) {
        return methods.contains(requestMethod);
    }

    @ToString @EqualsAndHashCode
    private class WhiteItem {
        public final Pattern pattern;

        public final Set<String> httpMethods;

        public WhiteItem(Pattern pattern, Set<String> httpMethods) {
            this.pattern = pattern;
            this.httpMethods = httpMethods;
        }
    }

    @ToString @EqualsAndHashCode
    private class AuthItem {
        public final Pattern pattern;

        public final Set<Integer> types;

        public AuthItem (Pattern pattern, Set<Integer> types) {
            this.pattern = pattern;
            this.types = types;
        }
    }
}
