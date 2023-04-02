package org.example.buy.controller;

import org.example.buy.entity.MyResponse;
import org.example.buy.service.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxl
 * @date 2023/4/2 上午11:32
 */

@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessServiceImpl businessService;

    public BusinessController(BusinessServiceImpl businessService) {
        this.businessService = businessService;
    }

    @PostMapping("/register")
    public MyResponse register(@RequestParam("people") String people,
                               @RequestParam("people_id") String people_id,
                               @RequestParam("phone_num") String phone_num,
                               @RequestParam("shopname") String shopname,
                               @RequestParam("register_amount") float register_amount) {
        return businessService.register(people, people_id, phone_num, shopname, register_amount);
    }

    @PostMapping("/login")
    public MyResponse login(@RequestParam("business_account") Integer business_account,
                            @RequestParam("business_password") String business_password) {
        return businessService.login(business_account, business_password);
    }

    @PostMapping("/historyOrder")
    public MyResponse historyOrder(@RequestParam("business_account") Integer business_account) {
        return businessService.historyOrder(business_account);
    }

    @PostMapping("/checkProduct")
    public MyResponse checkProduct(@RequestParam("business_account") Integer business_account) {
        return businessService.checkProduct(business_account);
    }

    @PostMapping("/changeProduct")
    public MyResponse changeProduct(@RequestParam("product_id") Integer product_id,
                                    @RequestParam("product_price") float product_price,
                                    @RequestParam("product_amount") Integer product_amount) {
        return businessService.changeProduct(product_id, product_price, product_amount);
    }

    @PostMapping("/addProduct")
    public MyResponse addProduct(@RequestParam("business_account") Integer business_account,
                                 @RequestParam("product_name") String product_name,
                                 @RequestParam("product_price") float product_price,
                                 @RequestParam("product_amount") Integer product_amount,
                                 @RequestParam("product_picture") byte[] product_picture,
                                 @RequestParam("product_msg") String product_msg) {
        return businessService.addProduct(business_account, product_name, product_price,
                product_amount, product_picture, product_msg);
    }

    @PostMapping("/deleteProduct")
    public MyResponse deleteProduct(@RequestParam("product_id") Integer product_id) {
        return businessService.deleteProduct(product_id);
    }

    @PostMapping("/changePass")
    public MyResponse changePass(@RequestParam("business_account") Integer business_account,
                                 @RequestParam("old_pass") String old_pass,
                                 @RequestParam("new_pass") String new_pass) {
        return businessService.changePass(business_account, old_pass, new_pass);
    }
}
