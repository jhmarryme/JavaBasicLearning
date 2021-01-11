package com.imooc.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * description: 
 * @author JiaHao Wang
 * @date 2021/1/8 12:33
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /**  
     * 获取openId的地址
     */
    public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**  
     * 获取qq userInfo的地址, 需要三个参数, 其中access_token 会由父类自动添加
     */
    public static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**  
     * 申请QQ登录成功后，分配给应用的appid
     */
    private final String appId;

    /**  
     * 用户的ID，与QQ号码一一对应。
     */
    private final String openId;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public QQUserInfo getQQUserInfo() {
        // 获取 qq用户信息
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);
        try {
            return objectMapper.readValue(result, QQUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        // 根据accessToken 获取 openId
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\"", "}");
    }
}
