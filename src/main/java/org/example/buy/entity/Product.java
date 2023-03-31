package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl
 * @date 2023/3/31 下午1:25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int pid;
    private String pname;
    private float amount;
    private int counts;
    private int bid;
    private byte[] img;
    private String msg;
}
