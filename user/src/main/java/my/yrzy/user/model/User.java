package my.yrzy.user.model;

import com.google.common.base.Objects;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangzefeng on 14/12/2
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -3411936161673651652L;

    private Long id;
    private String nickName;    //昵称
    private String realName;    //真实姓名
    private String password;    //加密密码
    private String phone;       //用户手机
    private Integer type;        //用户类型
    private Integer status;      //用户状态
    private Date createdAt;
    private Date updatedAt;

    public static enum TYPE {
        ADMIN(0, "管理员"),
        BUYER(1, "买家"),
        SELLER(2, "卖家");

        private final int value;

        private final String display;

        private TYPE(int number, String display) {
            this.value = number;
            this.display = display;
        }

        public static TYPE fromNumber(Integer number) {
            if (number == null) {
                return null;
            }
            for (TYPE type : TYPE.values()) {
                if (type.value == number) {
                    return type;
                }
            }
            return null;
        }

        public int toNumber() {
            return value;
        }

        @Override
        public String toString() {
            return display;
        }
    }

    public static enum Status {
        FROZEN(-1, "已冻结"),
        NOT_ACTIVATE(0, "未激活"),
        NORMAL(1, "正常");

        private final int value;

        private final String desc;

        private Status(int number, String desc) {
            this.value = number;
            this.desc = desc;
        }

        /**
         * 根据数值返回状态枚举
         * @param value 数值
         * @return 状态枚举
         */
        public static Status from(int value) {
            for (Status status : Status.values()) {
                if (Objects.equal(status.value, value)) {
                    return status;
                }
            }
            return null;
        }

        public int value() {
            return value;
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    public static enum LoginType{
        NAME(1, "名称"),
        MOBILE(2, "手机");

        private final Integer value;

        private final String display;

        LoginType(Integer value, String display) {
            this.value = value;
            this.display = display;
        }

        public static LoginType from(int value) {
            for (LoginType loginType : LoginType.values()) {
                if (Objects.equal(loginType.value, value)) {
                    return loginType;
                }
            }
            return null;
        }

        public int value() {
            return value;
        }

        @Override
        public String toString() {
            return display;
        }
    }
}
