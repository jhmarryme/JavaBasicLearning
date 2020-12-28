package com.imooc.security.core.validate.code.base;

import com.imooc.security.core.properties.SecurityConstants;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 2020/12/28 12:10
 * @modified By:
 */
public enum ValidateCodeTypeEnum {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * <br/>
     * @return java.lang.String
     */
    public abstract String getParamNameOnValidate();
}
