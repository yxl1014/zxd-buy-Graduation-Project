package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ${USER}
 * @date ${DATE} ${TIME}
 */

@SpringBootApplication
@MapperScan("org/example/buy/mapper")
public class BuyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuyApplication.class, args);
    }
}