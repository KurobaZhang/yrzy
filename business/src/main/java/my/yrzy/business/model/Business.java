package my.yrzy.business.model;

import com.google.common.base.Objects;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangzefeng on 14/12/2
 */
@Data
public class Business implements Serializable {
    private static final long serialVersionUID = 4490869091615832608L;

    private Long id;
    private String name;            //生意名称
    private Long shopId;            //店铺id
    private String shopName;        //店铺名称
    private String userName;        //商家名称
    private Long userId;            //发布生意的商家id
    private String discountInfo;    //生意信息(json)
    private Integer status;         //生意的状态
    private String mainImage;       //描述图片
    private Date createdAt;
    private Date updatedAt;

    public static enum Status {
        INIT(0, "未上架"),
        ON_SHELF(1, "上架"),
        OFF_SHELF(-1, "下架"),
        DELETED(-3, "删除");

        private int value;
        private String desc;

        private Status(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int value() {
            return value;
        }

        public static Status from(int number) {
            for (Status status : Status.values()) {
                if (Objects.equal(status.value, number)) {
                    return status;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return desc;
        }
    }
}
