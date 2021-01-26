package com.imooc.security.core.properties;

import lombok.Data;

/**
 * description: 
 * @Author: Wjh
 * @Date: 2020/11/9 11:23
 * @Modified By:
 */
@Data
public class BrowserProperties {

    /**
     * 提供默认的注册页面
     */
    private String signUpUrl = "/imooc-signUp.html";

    /**  
     * 提供默认的登录页面
     */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /**  
     * 默认的登录返回类型为JSON, 可配置为REDIRECT
     */
    private LoginType loginType = LoginType.JSON;

    /**
     * 记住我的默认时间
     */
    private int tokenValiditySeconds = 3600;
}
