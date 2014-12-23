package my.yrzy.common.util;

/**
 * Created by yangzefeng on 14/12/18
 */
public final class UserUtil {

    private static ThreadLocal<BaseUser> user = new ThreadLocal<BaseUser>();

    public static void putUser(BaseUser baseUser) {
        user.set(baseUser);
    }

    public static BaseUser getUser() {
        return user.get();
    }

    public static void cleanCurrentUser() {
        user.remove();
    }

    public static Long getUserId() {
        return user.get().getId();
    }
}
