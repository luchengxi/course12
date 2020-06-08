package com.course.utils;

import com.course.model.interfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    //读取配置文件的url地址信息
    private static ResourceBundle bundle = ResourceBundle.getBundle("UrlProperties", Locale.CHINA);

    public static String getUrl(interfaceName name){
        //获取url地址
        String addrs = bundle.getString("test.url");
        //获取接口地址
        String url="";
        //最终测试地址
        String testUrl;
        if(name== interfaceName.login){
            url=bundle.getString("login.url");
        }
        if(name== interfaceName.updateuserinfo){
            url=bundle.getString("updateUserInfo.url");
        }
        if(name== interfaceName.getuserlist){
            url=bundle.getString("getUserList.url");
        }
        if(name== interfaceName.getuserinfo){
            url=bundle.getString("getUserInfo.url");
        }
        if(name== interfaceName.adduserinfo){
            url=bundle.getString("addUser.url");
        }

        testUrl=addrs+url;
        return testUrl;
    }
}
