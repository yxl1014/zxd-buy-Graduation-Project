package org.example.buy.service;

import org.example.buy.common.RandomUtil;
import org.example.buy.entity.*;
import org.example.buy.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yxl
 * @date 2023/3/27 下午5:43
 */

@Service
public class ManageServiceImpl {

    private final ManageMapper manageMapper;

    private final BusinessMapper businessMapper;

    private final ProductMapper productMapper;

    private final ApplyMapper applyMapper;

    private final RandomUtil randomUtil;

    private final UserMapper userMapper;

    private final BuyCarMapper buyCarMapper;

    private final UBMapper ubMapper;

    public ManageServiceImpl(ManageMapper manageMapper, BusinessMapper businessMapper, ProductMapper productMapper, ApplyMapper applyMapper, RandomUtil randomUtil, UserMapper userMapper, BuyCarMapper buyCarMapper, UBMapper ubMapper) {
        this.manageMapper = manageMapper;
        this.businessMapper = businessMapper;
        this.productMapper = productMapper;
        this.applyMapper = applyMapper;
        this.randomUtil = randomUtil;
        this.userMapper = userMapper;
        this.buyCarMapper = buyCarMapper;
        this.ubMapper = ubMapper;
    }

    public MyResponse login(String account, String password) {
        int ok = manageMapper.findManageByAccountAndPassword(account, password);
        return new MyResponse(ok);
    }

    public MyResponse checkBusiness() {
        List<Business> businesses = businessMapper.selectAllBusiness();
        List<Integer> var1 = new ArrayList<>();
        List<String> var2 = new ArrayList<>();
        List<Integer> var3 = new ArrayList<>();
        List<String> var4 = new ArrayList<>();
        for (Business b : businesses) {
            var1.add(b.getBid());
            var2.add(b.getB_name());
            var3.add(productMapper.selectCountByBid(b.getBid()));
            var4.add(b.getCreate_time().toString());
        }
        return new MyResponse(1, var1.toArray(new Integer[0]), var2.toArray(new String[0]),
                var3.toArray(new Integer[0]), var4.toArray(new String[0]));
    }

    public MyResponse deleteBusiness(Integer account) {
        Business business = businessMapper.selectBusinessByBid(account);
        if (business == null) {
            return new MyResponse(0);
        }
        int ok = businessMapper.deleteBusinessByBid(account);
        productMapper.deleteProductByBid(account);
        return new MyResponse(ok);
    }

    public MyResponse takeAudit() {
        List<Apply> applies = applyMapper.selectAllApplyByStatus(false);
        List<Integer> var1 = new ArrayList<>();
        List<String> var2 = new ArrayList<>();
        List<String> var3 = new ArrayList<>();
        List<String> var4 = new ArrayList<>();
        List<String> var5 = new ArrayList<>();
        List<Float> var6 = new ArrayList<>();
        for (Apply apply : applies) {
            var1.add(apply.getAid());
            var2.add(apply.getApply_name());
            var3.add(apply.getApply_card_id());
            var4.add(apply.getApply_tel());
            var5.add(apply.getApply_bname());
            var6.add(apply.getRegister_amount());
        }
        return new MyResponse(1, var1, var2, var3, var4, var5, var6);
    }

    public MyResponse auditApply(Integer apply_id, Integer result) {
        Apply apply = applyMapper.selectApplyByAid(apply_id);
        if (apply == null) {
            return new MyResponse(0);
        }
        if (apply.isStatus()) {
            return new MyResponse(2);
        }
        if (result == 0) {
            int ok = applyMapper.deleteApplyByAid(apply_id);
            return new MyResponse(ok);
        }
        applyMapper.updateStatusByAid(apply_id, true);
        String password = randomUtil.getRandomPassword();
        Business business = new Business(password, apply.getApply_bname(), 0,
                apply.getRegister_amount(), new Timestamp(System.currentTimeMillis()), apply_id);
        int ok = businessMapper.insertBusiness(business);
        int bid = -1;
        if (ok == 1) {
            Business businessByAid = businessMapper.selectBusinessByAid(apply_id);
            bid = businessByAid.getBid();
        }
        return new MyResponse(1, bid, password);
    }

    public MyResponse checkUser() {
        List<User> users = userMapper.selectAllUser();
        List<String> var1 = new ArrayList<>();
        List<String> var2 = new ArrayList<>();
        for (User user : users) {
            var1.add(user.getAccount());
            var2.add(user.getCreate_time().toString());
        }
        return new MyResponse(1, var1, var2);
    }

    public MyResponse deleteUser(String account) {
        User user = userMapper.selectUserByAccount(account);
        if (user == null) {
            return new MyResponse(2);
        }
        int ok = userMapper.deleteUserByAccount(account);
        if (ok != 1) {
            return new MyResponse(0);
        }
        Buy_Car buy_car = buyCarMapper.selectBuyCarByAccount(account);
        int ok1 = buyCarMapper.deleteCarByCarId(buy_car.getCar_id());
        if (ok1 != 1) {
            return new MyResponse(0);
        }
        ubMapper.deleteUbByCarId(buy_car.getCar_id());
        return new MyResponse(1);
    }
}
