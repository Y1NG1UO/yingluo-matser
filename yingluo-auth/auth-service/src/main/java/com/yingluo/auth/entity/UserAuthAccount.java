package com.yingluo.auth.entity;

import com.yingluo.core.entity.BaseColEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户授权账号表
 * </p>
 *
 * @author heng
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAuthAccount extends BaseColEntity implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 第三方账号id
     */
    private String thirdAccountId;

    /**
     * 关联的第三方账户名称
     */
    private String thirdAccountName;

    /**
     * 第三方授权来源
     */
    private String sourceType;

}
