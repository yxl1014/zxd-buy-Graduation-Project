package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl
 * @date 2023/3/31 下午1:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
    private int aid;
    private String apply_name;
    private String apply_card_id;
    private String apply_tel;
    private String apply_bname;
    private float register_amount;
    private boolean status;//0:为审核，1：已审核通过，2：已审核拒绝，3：用户已删除
}
