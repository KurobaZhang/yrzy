package my.yrzy.trade.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangzefeng on 14/12/2
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 6438326501297062850L;

    private Long id;
    private Long shopId;    //店铺id
    private String shopName;    //店铺名称
    private Long sellerId;  //卖家id
    private String sellerName;  //卖家名称
    private Long buyerId;   //买家id
    private String buyerName;   //买家名称
    private Long businessId;    //生意id
    private String businessName;    //生意名称
    private Date createdAt;
    private Date updatedAt;
}
