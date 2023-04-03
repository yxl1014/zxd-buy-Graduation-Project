package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl
 * @date 2023/3/31 下午2:44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String account;
    private String password;
    private float all_amount;
    private Timestamp create_time;

    public User(String account, String password, Timestamp create_time) {
        this.account = account;
        this.password = password;
        this.create_time = create_time;
    }
}
