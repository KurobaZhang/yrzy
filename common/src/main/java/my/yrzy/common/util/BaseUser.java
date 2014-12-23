package my.yrzy.common.util;

import lombok.Data;

/**
 * Created by yangzefeng on 14/12/18
 */
@Data
public class BaseUser {

    private Long id;

    private String realName;

    private Integer type;

    private Integer status;
}
