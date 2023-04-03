package org.example.buy.controller;

import org.example.buy.entity.MyResponse;
import org.example.buy.service.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/searchProduct")
    public MyResponse searchProduct(@RequestParam("product_name") String product_name) {
        return consumerService.searchProduct(product_name);
    }

    @GetMapping("/takePicture")
    public byte[] takePicture(@RequestParam("product_id") Integer product_id) {
        return consumerService.takePicture(product_id);
    }

    @PostMapping("/takeProductMsg")
    public MyResponse takeProductMsg(@RequestParam("product_id") Integer product_id) {
        return consumerService.takeProductMsg(product_id);
    }

    @PostMapping("/takeBusinessMsg")
    public MyResponse takeBusinessMsg(@RequestParam("business_account") Integer business_account) {
        return consumerService.takeBusinessMsg(business_account);
    }

    @PostMapping("/buy")
    public MyResponse buy(@RequestParam("consumer_account") String consumer_account,
                          @RequestParam("product_id") String product_id,
                          @RequestParam("product_quantity") String product_quantity,
                          @RequestParam("pay_amount") Float pay_amount) {
        return consumerService.buy(consumer_account, product_id, product_quantity, pay_amount);
    }

    @PostMapping("/addBuyCar")
    public MyResponse addBuyCar(@RequestParam("consumer_account") String consumer_account,
                          @RequestParam("product_id") Integer product_id,
                          @RequestParam("product_quantity") Integer product_quantity) {
        return consumerService.addBuyCar(consumer_account, product_id, product_quantity);
    }

    @PostMapping("/deleteBuyCar")
    public MyResponse deleteBuyCar(@RequestParam("consumer_account") String consumer_account,
                                @RequestParam("product_id") Integer product_id) {
        return consumerService.deleteBuyCar(consumer_account, product_id);
    }

    @PostMapping("/checkBuyCar")
    public MyResponse checkBuyCar(@RequestParam("consumer_account") String consumer_account) {
        return consumerService.checkBuyCar(consumer_account);
    }

    @PostMapping("/historyOrder")
    public MyResponse historyOrder(@RequestParam("consumer_account") String consumer_account) {
        return consumerService.historyOrder(consumer_account);
    }

    @PostMapping("/changePass")
    public MyResponse changePass(@RequestParam("consumer_account") String consumer_account,
                                 @RequestParam("old_pass") String old_pass,
                                 @RequestParam("new_pass") String new_pass) {
        return consumerService.changePass(consumer_account, old_pass, new_pass);
    }
}
