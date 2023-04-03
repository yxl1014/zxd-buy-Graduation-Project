package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl
 * @date 2023/3/31 下午4:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UB {
    private int id;
    private int car_id;
    private int pid;
    private int counts;
    private Timestamp create_time;

    public UB(int car_id, int pid, int counts, Timestamp create_time) {
        this.car_id = car_id;
        this.pid = pid;
        this.counts = counts;
        this.create_time = create_time;
    }
}
