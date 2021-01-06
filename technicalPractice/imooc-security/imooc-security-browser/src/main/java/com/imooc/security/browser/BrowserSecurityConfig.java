package com.imooc.security.browser;

import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.config.SmsCodeFilter;
import com.imooc.security.core.validate.code.config.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * description: springsecurity 配置
 * @Author: Wjh
 * @Date: 2020/10/29 12:23
 * @Modified By:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 对密码 使用加密处理
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    /**
     * rememberme 配置
     * <br/>
     * @author Jiahao Wang
     * @date 2020/12/7 9:15
     * @param
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     * @throws
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 在首次运行时 在数据库中创建表
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 配置自定义的验证码处理过滤器
//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
//        validateCodeFilter.setSecurityProperties(securityProperties);
        // 需要手动执行
//        validateCodeFilter.afterPropertiesSet();
//        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//        smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
//        smsCodeFilter.setSecurityProperties(securityProperties);
//        // 需要手动执行
//        smsCodeFilter.afterPropertiesSet();


        // 在 UsernamePasswordAuthenticationFilter 前增加一个 ValidateCodeFilter/smsCodeFilter 用于处理图形验证码/短信验证码逻辑
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                // 当未登录时, 跳转的路径
                    .loginPage("/authentication/require")
                // 登录请求的处理路径
                    .loginProcessingUrl("/authentication/form")
                // 认证 成功/失败 的处理逻辑
                    .successHandler(imoocAuthenticationSuccessHandler)
                    .failureHandler(imoocAuthenticationFailureHandler)
                    .and()
                // 配置remember me功能
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getTokenValiditySeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .authorizeRequests()
                // 不需要登录的路径
                    .antMatchers("/authentication/require",
                            securityProperties.getBrowser().getLoginPage(),
                            "/code/*").permitAll()
                // 其他所有请求都需要认证
                    .anyRequest()
                    .authenticated()
                    .and()
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);
    }
}
