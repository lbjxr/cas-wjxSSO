package com.lbj.controller;

import com.lbj.Application;
import com.lbj.util.WjxSSOUtil;
import com.lbj.util.CasUtil;
import com.lbj.util.UnicodeDecoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


@SuppressWarnings("unused")
@Controller
public class CasWJXSSOController {

    @Value("${ssoUrl1}")
    private String ssoUrl1;
    @Value("${ssou1}")
    private String ssou1;
    @Value("${ssou2}")
    private String ssou2;
    @Value("${ssouserSystem}")
    private String ssouserSystem;
    @Value("${appId1}")
    private  String appId1;
    @Value("${appKey1}")
    private  String appKey1;

    @Autowired
    private WjxSSOUtil wjxSSOUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * 单点登录问卷星特定用户体系。根据url中systemId拼接请求地址,activity为具体问卷id。可以传或者不传
     */
    @RequestMapping(value = "/wjxSSO",method = RequestMethod.GET)
    public String wjxSSO(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        //获取问卷星用户体系id
        String systemId = request.getParameter("systemId");
//        获取问卷id
        String activity = request.getParameter("activity") != null && request.getParameter("activity").length() != 0 ? request.getParameter("activity") : "";
        //获取当前登录用户id
        String uid = Objects.requireNonNull(CasUtil.getUserInfoFromCas(request)).getUserAccount();
        //参数u
        String u = URLEncoder.encode(UnicodeDecoding.unicodeToString1(ssou1),"UTF-8") + ssou2;

        LOGGER.info("url参数systemId: "+ request.getParameter("systemId"));
        LOGGER.info("url参数activity: "+ request.getParameter("activity"));

        //拼接请求地址
        String HttpSSoUrl = ssoUrl1
                + "?u=" + u
                + "&userSystem=" + ssouserSystem
                + "&systemId=" + systemId
                + "&appid=" + appId1
                + "&activity=" + activity
                + "&uid=" + uid + WjxSSOUtil.getSign(appId1,appKey1,uid);

//        System.out.println("请求地址： " + HttpSSoUrl);
        LOGGER.info("请求地址：" + HttpSSoUrl);
//       重定向到问卷星单点登录地址
        response.sendRedirect(HttpSSoUrl);

        return "200";
    }
}
