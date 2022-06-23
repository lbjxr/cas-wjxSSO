package com.lbj.util;

import com.lbj.bean.UserInfo;
import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class CasUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUtil.class);
    /**
     * cas client 默认的session key
     */
    public final static String CAS = "_const_cas_assertion_";

    /**
     * 封装UserInfo
     *
     * @param request
     * @return
     */
    public static UserInfo getUserInfoFromCas (HttpServletRequest request) {
        Object object = request.getSession().getAttribute(CAS);
        if ( null == object ) {
            return null;
        }
        Assertion assertion = ( Assertion ) object;
        return buildUserInfoByCas(assertion);
    }

    /**
     * 构建UserInfo
     *
     * @param assertion
     * @return
     */
    private static UserInfo buildUserInfoByCas (Assertion assertion) {
        if ( null == assertion ) {
            LOGGER.error("cas对接buildUserInfoByCas没有获取到用户");
            return null;
        }
        UserInfo userInfo = new UserInfo();
        String userName = assertion.getPrincipal().getName();
        LOGGER.info("cas对接登录用户buildUserInfoByCas：" + userName);
        userInfo.setUserAccount(userName);
        //获取属性值
        Map<String, Object> attributes = assertion.getPrincipal().getAttributes();
        Object name = attributes.get("cn");
        userInfo.setUserName(name == null ? userName : name.toString());

        userInfo.setAttributes(attributes);
        return userInfo;
    }

}
