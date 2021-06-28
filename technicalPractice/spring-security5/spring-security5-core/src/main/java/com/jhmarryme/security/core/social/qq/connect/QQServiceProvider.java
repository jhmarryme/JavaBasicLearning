package com.jhmarryme.security.core.social.qq.connect;

import com.jhmarryme.security.core.social.qq.api.QQ;
import com.jhmarryme.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 服务提供商
 * @author JiaHao Wang
 * @date 2021/1/11 11:39
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    /**  
     * 获取Authorization Code 请求地址
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**  
     * 通过Authorization Code获取Access Token 请求地址
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }


    @Override
    public QQ getApi(String accessToken) {
        // 为每一个用户创建一个不同的QQImpl
        return new QQImpl(accessToken, appId);
    }
}
