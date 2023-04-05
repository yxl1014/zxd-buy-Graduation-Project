package org.example.buy.service;

import org.example.buy.entity.*;
import org.example.buy.mapper.ApplyMapper;
import org.example.buy.mapper.BusinessMapper;
import org.example.buy.mapper.OrderMapper;
import org.example.buy.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yxl
 * @date 2023/4/2 上午11:33
 */

@Service
public class BusinessServiceImpl {

    private final BusinessMapper businessMapper;

    private final ApplyMapper applyMapper;

    private final OrderMapper orderMapper;

    private final ProductMapper productMapper;

    public BusinessServiceImpl(ApplyMapper applyMapper, BusinessMapper businessMapper, OrderMapper orderMapper, ProductMapper productMapper) {
        this.applyMapper = applyMapper;
        this.businessMapper = businessMapper;
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
    }

    public MyResponse register(String people, String people_id, String phone_num, String shopname, float register_amount) {
        Business business = businessMapper.selectBusinessByBName(shopname);

        if (business != null) {
            return new MyResponse(2);
        }

        int ok = applyMapper.insertApply(new Apply(people, people_id, phone_num, shopname, register_amount));
        return new MyResponse(ok);
    }

    public MyResponse login(Integer business_account, String business_password) {
        Business business = businessMapper.selectBusinessByBidAndPassword(business_account, business_password);
        return new MyResponse(business == null ? 0 : 1);
    }

    public MyResponse historyOrder(Integer business_account) {
        List<Order_> order_s = orderMapper.selectAllByBid(business_account);
        List<Integer> var1 = new ArrayList<>();
        List<List<String>> var2 = new ArrayList<>();
        List<String> var3 = new ArrayList<>();
        List<Float> var4 = new ArrayList<>();
        List<String> var5 = new ArrayList<>();
        for (Order_ o : order_s) {
            var1.add(o.getOid());
            String[] pids = o.getPid().split("_");
            List<String> names = new ArrayList<>();
            for (String p : pids) {
                Product product = productMapper.selectProductByPid(Integer.parseInt(p));
                if (product != null) {
                    names.add(product.getPname());
                } else {
                    names.add(null);
                }
            }
            var2.add(names);
            var3.add(o.getUser_account());
            var4.add(o.getAll_amount());
            var5.add(o.getOrder_time().toString());
        }
        return new MyResponse(1, var1, var2, var3, var4, var5);
    }

    public MyResponse checkProduct(Integer business_account) {
        Business business = businessMapper.selectBusinessByBid(business_account);
        if (business == null) {
            return new MyResponse(2);
        }
        List<Product> products = productMapper.selectProductByBid(business_account);
        List<Integer> var1 = new ArrayList<>();
        List<String> var2 = new ArrayList<>();
        List<Float> var3 = new ArrayList<>();
        List<Integer> var4 = new ArrayList<>();
        List<String> var5 = new ArrayList<>();
        for (Product p : products) {
            var1.add(p.getPid());
            var2.add(p.getPname());
            var3.add(p.getAmount());
            var4.add(p.getCounts());
            var5.add(p.getMsg());
        }
        return new MyResponse(var1, var2, var3, var4, var5);
    }

    public MyResponse changeProduct(Integer product_id, float product_price, Integer product_amount) {
        Product product = productMapper.selectProductByPid(product_id);
        if (product == null) {
            return new MyResponse(2);
        }
        int i = productMapper.updateProduct(product_price, product_amount, product_id);
        return new MyResponse(i);
    }

    public MyResponse addProduct(Integer business_account, String product_name, float product_price,
                                 Integer product_amount, MultipartFile product_picture, String product_msg) {
        Business business = businessMapper.selectBusinessByBid(business_account);
        if (business == null) {
            return new MyResponse(2);
        }
        byte[] img = null;
        try {
            img = product_picture.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        businessMapper.updateCountByBid(business.getProduct_count() + 1, business.getBid());
        int ok = productMapper.insertProduct(new Product(product_name, product_price, product_amount, business_account,
                img, product_msg));
        return new MyResponse(ok);
    }

    public MyResponse deleteProduct(Integer product_id) {
        Product product = productMapper.selectProductByPid(product_id);
        if (product == null) {
            return new MyResponse(2);
        }
        int ok = productMapper.deleteProductByPid(product_id);
        return new MyResponse(ok);
    }

    public MyResponse changePass(Integer business_account, String old_pass, String new_pass) {
        Business business = businessMapper.selectBusinessByBidAndPassword(business_account, old_pass);
        if (business == null) {
            return new MyResponse(2);
        }

        int ok = businessMapper.updatePasswordByBid(business_account, new_pass);

        return new MyResponse(ok);
    }
}
