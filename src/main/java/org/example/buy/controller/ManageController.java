package org.example.buy.controller;

import org.example.buy.entity.MyResponse;
import org.example.buy.service.ManageServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * @author yxl
 * @date 2023/3/27 下午5:42
 */

@RestController
@RequestMapping("/manage")
public class ManageController {
    private final ManageServiceImpl manageService;

    public ManageController(ManageServiceImpl manageService) {
        this.manageService = manageService;
    }

    @PostMapping("/login")
    public MyResponse login(@RequestParam("manage_account") String account,
                            @RequestParam("manage_password") String password) {
        return manageService.login(account, password);
    }

    @PostMapping("/checkBusiness")
    public MyResponse checkBusiness() {
        return manageService.checkBusiness();
    }

    @PostMapping("/deleteBusiness")
    public MyResponse deleteBusiness(@RequestParam("business_account") Integer account) {
        return manageService.deleteBusiness(account);
    }

    @PostMapping("/takeAudit")
    public MyResponse takeAudit() {
        return manageService.takeAudit();
    }

    @PostMapping("/auditApply")
    public MyResponse auditApply(@RequestParam("apply_id") Integer apply_id,
                            @RequestParam("result") Integer result) {
        return manageService.auditApply(apply_id, result);
    }

    @PostMapping("/checkUser")
    public MyResponse checkUser() {
        return manageService.checkUser();
    }

    @PostMapping("/deleteUser")
    public MyResponse deleteUser(@RequestParam("consumer_account") String account) {
        return manageService.deleteUser(account);
    }
}
