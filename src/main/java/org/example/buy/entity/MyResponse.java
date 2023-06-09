package org.example.buy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.buy.mapper.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yxl
 * @date 2023/3/27 下午5:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyResponse {

    private int status;

    public MyResponse(int status) {
        this.status = status;
    }

    private Integer[] business_account;
    private String[] business_shopname;
    private Integer[] product_amount;
    private String[] business_date;

    public MyResponse(int status, Integer[] business_account, String[] business_shopname, Integer[] product_amount, String[] business_date) {
        this.status = status;
        this.business_account = business_account;
        this.business_shopname = business_shopname;
        this.product_amount = product_amount;
        this.business_date = business_date;
    }

    private List<Integer> apply_id;
    private List<String> apply_people;
    private List<String> apply_idnum;
    private List<String> apply_phone;
    private List<String> apply_shopname;
    private List<Float> apply_amount;

    public MyResponse(int status, List<Integer> apply_id, List<String> apply_people, List<String> apply_idnum,
                      List<String> apply_phone, List<String> apply_shopname, List<Float> apply_amount) {
        this.status = status;
        this.apply_id = apply_id;
        this.apply_people = apply_people;
        this.apply_idnum = apply_idnum;
        this.apply_phone = apply_phone;
        this.apply_shopname = apply_shopname;
        this.apply_amount = apply_amount;
    }


    private Integer bid;
    private String bpassword;

    public MyResponse(int i, int bid, String password) {
        this.status = i;
        this.bid = bid;
        this.bpassword = password;
    }

    private List<String> consumer_account;
    private List<String> consumer_date;

    public MyResponse(int status, List<String> consumer_account, List<String> consumer_date) {
        this.status = status;
        this.consumer_account = consumer_account;
        this.consumer_date = consumer_date;
    }

    private List<Integer> order_id;
    private List<List<String>> product_name;
    private List<String> user;
    private List<Float> pay_amount;
    private List<String> date;

    public MyResponse(int status, List<Integer> order_id, List<List<String>> product_name,
                      List<String> user, List<Float> pay_amount, List<String> date) {
        this.status = status;
        this.order_id = order_id;
        this.product_name = product_name;
        this.user = user;
        this.pay_amount = pay_amount;
        this.date = date;
    }

    private List<Integer> product_id;
    private List<String> product_name_;
    private List<Float> product_price;
    private List<Integer> product_amount_;
    private List<String> product_msg;

    public MyResponse(List<Integer> product_id, List<String> product_name_, List<Float> product_price,
                      List<Integer> product_amount_, List<String> product_msg) {
        this.product_id = product_id;
        this.product_name_ = product_name_;
        this.product_price = product_price;
        this.product_amount_ = product_amount_;
        this.product_msg = product_msg;
    }

    private List<Integer> random_id;
    private List<String> random_name;
    private List<Float> random_price;
    private List<Integer> random_num;

    public MyResponse(List<Integer> random_id, List<String> random_name, List<Float> random_price, List<Integer> random_num) {
        this.random_id = random_id;
        this.random_name = random_name;
        this.random_price = random_price;
        this.random_num = random_num;
    }

    private List<String> product_shopname;

    public MyResponse(int status, List<String> product_name_, List<Integer> product_id, List<String> product_shopname) {
        this.status = status;
        this.product_id = product_id;
        this.product_name_ = product_name_;
        this.product_shopname = product_shopname;
    }

    private Product product;

    public MyResponse(int status, Product product) {
        this.status = status;
        this.product = product;
    }

    private String shopname;
    private Integer product_quantity;

    public MyResponse(int status, String shopname, Integer product_quantity,
                      List<String> product_name_, List<Integer> product_id,
                      List<Float> product_price) {
        this.status = status;
        this.product_id = product_id;
        this.product_name_ = product_name_;
        this.product_price = product_price;
        this.shopname = shopname;
        this.product_quantity = product_quantity;
    }

    private List<Integer> product_quantity_;

    public MyResponse(int status, List<String> product_name_, List<Integer> product_id,
                      List<Float> product_price, List<String> product_msg, List<Integer> product_quantity, String xxx) {
        this.status = status;
        this.product_id = product_id;
        this.product_name_ = product_name_;
        this.product_price = product_price;
        this.product_msg = product_msg;
        this.product_quantity_ = product_quantity;
    }

    private List<String> shopname_;

    private List<Float> product_amount__;
    private List<String> create_time;

    public MyResponse(int status, List<List<String>> product_name, List<Float> product_amount_,
                      List<String> shopname, List<String> create_time, boolean flag1, boolean flag2, boolean flag3) {
        this.status = status;
        this.product_name = product_name;
        this.product_amount__ = product_amount_;
        this.shopname_ = shopname;
        this.create_time = create_time;
    }
}
