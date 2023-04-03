package org.example.buy.controller;

import org.example.buy.entity.MyResponse;
import org.example.buy.service.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxl
 * @date 2023/4/2 下午12:58
 */

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @PostMapping("/register")
    public MyResponse register(@RequestParam("consumer_account") String consumer_account,
                               @RequestParam("consumer_password") String consumer_password) {
        return consumerService.register(consumer_account, consumer_password);
    }

    @PostMapping("/login")
    public MyResponse login(@RequestParam("consumer_account") String consumer_account,
                               @RequestParam("consumer_password") String consumer_password) {
        return consumerService.login(consumer_account, consumer_password);
    }

    @PostMapping("/takeRandomMsg")
    public MyResponse takeRandomMsg() {
        return consumerService.takeRandomMsg();
    }
}
