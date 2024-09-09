package com.yingluo.seata.feign;

import com.yingluo.core.info.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yingluo-auth")
public interface AuthFeignClient {

    /**
     * 为用户增加角色
     */
    @PostMapping("/user/role/add")
    Result<String> addRole(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId);
}
