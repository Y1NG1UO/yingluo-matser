package com.yingluo.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yingluo.auth.entity.User;
import com.yingluo.auth.mapper.UserMapper;
import com.yingluo.auth.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author heng
 */
@Service
@Slf4j
public class UserAccountServiceImpl extends ServiceImpl<UserMapper, User> implements UserAccountService {


}
