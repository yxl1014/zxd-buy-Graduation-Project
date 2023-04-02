package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl
 * @date 2023/4/2 上午11:49
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order_ {
    private int oid;
    private int bid;
    private String pid;
    private String pCount;
    private String user_account;
    private float all_amount;
    private Timestamp order_time;
}
