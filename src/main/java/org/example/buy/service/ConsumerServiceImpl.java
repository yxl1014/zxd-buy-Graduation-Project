package org.example.buy.service;

import org.example.buy.common.RandomUtil;
import org.example.buy.entity.MyResponse;
import org.example.buy.entity.Product;
import org.example.buy.entity.User;
import org.example.buy.mapper.ProductMapper;
import org.example.buy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author yxl
 * @date 2023/4/2 下午12:59
 */

@Service
public class ConsumerServiceImpl {

    private final UserMapper userMapper;

    @Autowired
    private RandomUtil randomUtil;

    @Autowired
    private ProductMapper productMapper;

    public ConsumerServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public MyResponse register(String consumer_account, String consumer_password) {
        User user = userMapper.selectUserByAccount(consumer_account);
        if (user != null) {
            return new MyResponse(0);
        }
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
}
