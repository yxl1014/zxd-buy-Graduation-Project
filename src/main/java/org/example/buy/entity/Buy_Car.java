package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl
 * @date 2023/3/31 下午4:00
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buy_Car {
    private int car_id;
    private String user_account;
    private int counts;
}
