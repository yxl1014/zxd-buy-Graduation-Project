package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl
 * @date 2023/3/28 下午2:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    private int bid;
    private String b_password;
    private String b_name;
    private int product_count;
    private float all_amount;
    private Timestamp create_time;
    private int apply_id;

    public Business(String b_password, String b_name, int product_count, float all_amount, Timestamp create_time, int apply_id) {
        this.b_password = b_password;
        this.b_name = b_name;
        this.product_count = product_count;
        this.all_amount = all_amount;
        this.create_time = create_time;
        this.apply_id = apply_id;
    }
}
