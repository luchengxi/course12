package com.course.config;

import com.course.model.interfaceName;
import com.course.utils.ConfigFile;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BeforeTestPrepare {
    @BeforeSuite(description = "套件测试开始，测试准备工作，Httpclient，拼接URL")
    public void beforeSuit(){
        //准备接口地址
        TestConfig.loginUrl= ConfigFile.getUrl(interfaceName.login);
        TestConfig.addUserUrl=ConfigFile.getUrl(interfaceName.adduserinfo);
        TestConfig.getUserInfoUrl=ConfigFile.getUrl(interfaceName.getuserinfo);
        TestConfig.getUserListUrl=ConfigFile.getUrl(interfaceName.getuserlist);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(interfaceName.updateuserinfo);
        //创建HTTP连接
        TestConfig.closeableHttpClient= HttpClients.custom().setDefaultCookieStore(TestConfig.getBasicCookieStore()).build();
    }
    @AfterSuite(description = "套件测试结束")
    public void afterSuit(){
        System.out.println("afterSuit.........run");
    }
}
