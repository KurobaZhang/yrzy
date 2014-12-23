package my.yrzy.common.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by yangzefeng on 14/12/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = -4587311033636101010L;

    private Long total;

    private List<T> data;

    public static <T> Paging<T> empty() {
        List<T> emptyList = Collections.emptyList();
        return new Paging<T>(0L, emptyList);
    }
}
