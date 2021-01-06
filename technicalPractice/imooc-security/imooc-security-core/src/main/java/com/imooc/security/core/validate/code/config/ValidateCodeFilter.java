package com.imooc.security.core.validate.code.config;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeProcessorHolder;
import com.imooc.security.core.validate.code.ValidateCodeProcessor;
import com.imooc.security.core.validate.code.base.ImageCode;
import com.imooc.security.core.validate.code.base.ValidateCodeException;
import com.imooc.security.core.validate.code.base.ValidateCodeTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * description: 验证码处理 filter
 *      InitializingBean接口为bean提供了属性初始化后的处理方法，
 *      它只包括afterPropertiesSet方法，凡是继承该接口的类，在bean的属性初始化后都会执行该方法。
 * @author: JiaHao Wang
 * @date: 2020/11/30 17:53
 * @modified By:
 */
@Data
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 系统配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放需要拦截的urls
     */
    private Map<String, ValidateCodeTypeEnum> urlMap = new HashMap<>();

    /**
     * 该方法是在属性设置后才调用的。
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        // 添加图形验证码url
        addUrlToMap(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeTypeEnum.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeTypeEnum.IMAGE);

        // 添加短信验证码url
        addUrlToMap(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeTypeEnum.SMS);
        addUrlToMap(securityProperties.getCode().getSmsCode().getUrl(), ValidateCodeTypeEnum.SMS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeTypeEnum type = getValidateCodeTypeEnum(request);

        if (type != null) {
            log.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
                log.info("验证码校验通过");
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     * <br/>
     * @author Jiahao Wang
     * @date 2020/12/28 12:49
     * @param request
     * @return com.imooc.security.core.validate.code.base.ValidateCodeTypeEnum
     * @throws
     */
    private ValidateCodeTypeEnum getValidateCodeTypeEnum(HttpServletRequest request) {
        ValidateCodeTypeEnum result = null;
        if (!StringUtils.equalsIgnoreCase(request.getRequestURI(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url: urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }

    /**
     * 验证码校验逻辑
     * <br/>
     * @author Jiahao Wang
     * @date 2020/12/7 11:34
     * @param request
     * @return void
     * @throws
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request,
                ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
    }

    /**
     * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
     * <br/>
     * @author Jiahao Wang
     * @date 2020/12/28 12:29
     * @param urls
     * @param type
     * @return void
     */
    private void addUrlToMap(String urls, ValidateCodeTypeEnum type) {
        if (StringUtils.isNotBlank(urls)) {
            for (String url : StringUtils.split(urls, ",")) {
                urlMap.put(url, type);
            }
        }
    }

}
