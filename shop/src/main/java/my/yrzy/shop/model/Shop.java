package my.yrzy.shop.model;

import com.google.common.base.Objects;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangzefeng on 14/12/2
 */
@Data
public class Shop implements Serializable {
    private static final long serialVersionUID = -8830071504711211423L;

    private Long id;
    private Long userId;    //商家id
    private String userName;    //商家名称
    private String shopName;    //店铺名称
    private Integer status;     //店铺状态
    private String phone;       //店铺联系方式
    private String telephone;   //店铺固定电话
    private String imageUrl;    //店铺主图
    private String address;     //店铺地址（json）
    private Date createdAt;
    private Date updatedAt;

    public static enum Status {
        INIT(0), OK(1), FAIL(-2), FROZEN(-1);

        private final int value;

        private Status(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static Status from(Integer value) {
            for (Status status : Status.values()) {
                if (Objects.equal(status.value, value)) {
                    return status;
                }
            }
            return null;
        }
    }
}
