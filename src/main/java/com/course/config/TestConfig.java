package com.course.config;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConfig {
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserInfoUrl;
    public static String getUserListUrl;
    public static String addUserUrl;

    public static CloseableHttpClient closeableHttpClient;
    public static BasicCookieStore basicCookieStore;
    public static Logger log = LoggerFactory.getLogger(TestConfig.class); ;

    public static BasicCookieStore getBasicCookieStore(){
        basicCookieStore=new BasicCookieStore();
        return basicCookieStore;
    }

}
