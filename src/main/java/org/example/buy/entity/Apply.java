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

    public Apply(String apply_name, String apply_card_id, String apply_tel, String apply_bname, float register_amount) {
        this.apply_name = apply_name;
        this.apply_card_id = apply_card_id;
        this.apply_tel = apply_tel;
        this.apply_bname = apply_bname;
        this.register_amount = register_amount;
    }
}
