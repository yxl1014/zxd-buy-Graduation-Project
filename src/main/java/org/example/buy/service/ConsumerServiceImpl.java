package org.example.buy.service;

import org.example.buy.common.RandomUtil;
import org.example.buy.entity.*;
import org.example.buy.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author yxl
 * @date 2023/4/2 下午12:59
 */

@Service
public class ConsumerServiceImpl {

    private final UserMapper userMapper;

    private final RandomUtil randomUtil;

    private final ProductMapper productMapper;

    private final BusinessMapper businessMapper;


    private final OrderMapper orderMapper;

    private final BuyCarMapper buyCarMapper;

    private final UBMapper ubMapper;

    public ConsumerServiceImpl(UserMapper userMapper, RandomUtil randomUtil, ProductMapper productMapper, BusinessMapper businessMapper, OrderMapper orderMapper, UBMapper ubMapper, BuyCarMapper buyCarMapper) {
        this.userMapper = userMapper;
        this.randomUtil = randomUtil;
        this.productMapper = productMapper;
        this.businessMapper = businessMapper;
        this.orderMapper = orderMapper;
        this.ubMapper = ubMapper;
        this.buyCarMapper = buyCarMapper;
    }

    public MyResponse register(String consumer_account, String consumer_password) {
        User user = userMapper.selectUserByAccount(consumer_account);
        if (user != null) {
            return new MyResponse(0);
        }
        buyCarMapper.insertBuyCar(consumer_account);
        int ok = userMapper.insertUser(new User(consumer_account, consumer_password, new Timestamp(System.currentTimeMillis())));
        return new MyResponse(ok);
    }

    public MyResponse login(String consumer_account, String consumer_password) {
        User user = userMapper.selectUserByAccountAndPassword(consumer_account, consumer_password);
        return new MyResponse(user != null ? 1 : 0);
    }

    public MyResponse takeRandomMsg() {
        int size = productMapper.selectCount();
        HashSet<Integer> integers = randomUtil.randomPid(size + 1);
        List<Integer> var1 = new ArrayList<>();
        List<String> var2 = new ArrayList<>();
        List<Float> var3 = new ArrayList<>();
        List<Integer> var4 = new ArrayList<>();
        for (int i : integers) {
            Product product = productMapper.selectProductByPid(i);
            if (product == null) {
                continue;
            }
            var1.add(product.getPid());
            var2.add(product.getPname());
            var3.add(product.getAmount());
            var4.add(product.getBid());
        }
        return new MyResponse(var1, var2, var3, var4);
    }

    public MyResponse searchProduct(String product_name) {
        List<Product> products = productMapper.selectProductByPName(product_name);
        if (products.size() == 0) {
            return new MyResponse(0);
        }
        List<String> var1 = new ArrayList<>();
        List<Integer> var2 = new ArrayList<>();
        List<String> var3 = new ArrayList<>();
        for (Product p : products) {
            var1.add(p.getPname());
            var2.add(p.getPid());
            Business business = businessMapper.selectBusinessByBid(p.getBid());
            if (business != null) {
                var3.add(business.getB_name());
            } else {
                var3.add(null);
            }
        }
        return new MyResponse(1, var1, var2, var3);
    }

    public byte[] takePicture(Integer product_id) {
        Product product = productMapper.selectProductByPid(product_id);
        if (product == null) {
            return null;
        }
        return product.getImg();
    }

    public MyResponse takeProductMsg(Integer product_id) {
        Product product = productMapper.selectProductByPid(product_id);
        return new MyResponse(1, product);
    }

    public MyResponse takeBusinessMsg(Integer business_account) {
        Business business = businessMapper.selectBusinessByBid(business_account);
        if (business == null) {
            return new MyResponse(0);
        }
        List<String> var1 = new ArrayList<>();
        List<Integer> var2 = new ArrayList<>();
        List<Float> var3 = new ArrayList<>();
        List<Product> products = productMapper.selectProductByBid(business_account);
        for (Product product : products) {
            var1.add(product.getPname());
            var2.add(product.getPid());
            var3.add(product.getAmount());
        }

        return new MyResponse(1, business.getB_name(), products.size(), var1, var2, var3);
    }

    public MyResponse buy(String consumer_account, String product_id, String product_quantity, Float pay_amount) {
        User user = userMapper.selectUserByAccount(consumer_account);
        String[] ids = product_id.split("_");
        String[] quantities = product_quantity.split("_");
        int len = ids.length;
        int ok1 = userMapper.updateUserAmountByAccount(user.getAll_amount() + pay_amount, consumer_account);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Product product = productMapper.selectProductByPid(Integer.parseInt(ids[i]));
            if (product == null) {
                continue;
            }
            List<Integer> value = map.computeIfAbsent(product.getBid(), k -> new ArrayList<>());
            value.add(i);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Business business = businessMapper.selectBusinessByBid(entry.getKey());
            if (business == null) {
                continue;
            }
            StringBuilder pid = new StringBuilder();
            StringBuilder pCount = new StringBuilder();
            float amount = 0;
            for (Integer index : entry.getValue()) {
                int id = Integer.parseInt(ids[index]);
                int quantity = Integer.parseInt(quantities[index]);
                pid.append(id).append("_");
                pCount.append(quantity).append("_");
                Product product = productMapper.selectProductByPid(id);
                amount += product.getAmount() * quantity;
            }
            Order_ order_ = new Order_(business.getBid(), pid.toString(), pCount.toString(),
                    consumer_account, amount, new Timestamp(System.currentTimeMillis()));
            orderMapper.insertOrder(order_);
            businessMapper.updateAmountByBid(business.getAll_amount() + amount, business.getBid());
        }
        return new MyResponse(ok1);
    }

    public MyResponse addBuyCar(String consumer_account, Integer product_id, Integer product_quantity) {
        Buy_Car buy_car = buyCarMapper.selectBuyCarByAccount(consumer_account);
        if (buy_car == null) {
            return new MyResponse(0);
        }
        int ok = ubMapper.insertUB(new UB(buy_car.getCar_id(), product_id,
                product_quantity, new Timestamp(System.currentTimeMillis())));
        int ok1 = buyCarMapper.updateCountsById(buy_car.getCar_id());
        return new MyResponse(ok + ok1 == 2 ? 1 : 0);
    }

    public MyResponse deleteBuyCar(String consumer_account, Integer product_id) {
        Buy_Car buy_car = buyCarMapper.selectBuyCarByAccount(consumer_account);
        int ok = ubMapper.deleteUbByCarIdAndPid(buy_car.getCar_id(), product_id);
        return new MyResponse(ok);
    }

    public MyResponse checkBuyCar(String consumer_account) {
        Buy_Car buy_car = buyCarMapper.selectBuyCarByAccount(consumer_account);
        List<UB> ubs = ubMapper.selectUbByCarId(buy_car.getCar_id());
        List<String> var1 = new ArrayList<>();
        List<Integer> var2 = new ArrayList<>();
        List<Float> var3 = new ArrayList<>();
        List<String> var4 = new ArrayList<>();
        List<Integer> var5 = new ArrayList<>();
        for (UB ub : ubs) {
            Product product = productMapper.selectProductByPid(ub.getPid());
            if (product == null) {
                continue;
            }
            var1.add(product.getPname());
            var2.add(product.getPid());
            var3.add(product.getAmount());
            var4.add(product.getMsg());
            var5.add(ub.getCounts());
        }
        return new MyResponse(1, var1, var2, var3, var4, var5, null);
    }

    public MyResponse historyOrder(String consumer_account) {
        List<Order_> order_s = orderMapper.selectAllByAccount(consumer_account);
        List<List<String>> var1 = new ArrayList<>();
        List<Float> var2 = new ArrayList<>();
        List<String> var3 = new ArrayList<>();
        List<String> var4 = new ArrayList<>();
        for (Order_ o : order_s) {
            String[] ids = o.getPid().split("_");
            List<String> var = new ArrayList<>();
            for (String id : ids) {
                Product product = productMapper.selectProductByPid(Integer.parseInt(id));
                var.add(product.getPname());
            }
            var1.add(var);
            var2.add(o.getAll_amount());
            Business business = businessMapper.selectBusinessByBid(o.getBid());
            var3.add(business.getB_name());
            var4.add(o.getOrder_time().toString());
        }
        return new MyResponse(1, var1, var2, var3, var4, true, true, true);
    }

    public MyResponse changePass(String consumer_account, String old_pass, String new_pass) {
        User user = userMapper.selectUserByAccountAndPassword(consumer_account, old_pass);
        if (user == null) {
            return new MyResponse(2);
        }
        int i = userMapper.updatePasswordByAccount(new_pass, consumer_account);
        return new MyResponse(i);
    }
}
