package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.loginCase;
import com.course.utils.MybatisSqlSession;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;


public class LoginTest {

    @Test(description = "登录接口测试")
    public void loginTest() throws IOException {
        //从数据库中获取参数
        SqlSession sqlsession = MybatisSqlSession.getSqlsession();
        loginCase loginCase = sqlsession.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        TestConfig.log.info("登录接口测试地址"+TestConfig.loginUrl);

        //接口调用，获取结果
        String result = getResult(loginCase);
        TestConfig.log.info("期望结果为"+loginCase.getExpected());
        //验证结果
        Assert.assertEquals(loginCase.getExpected(),result);
        TestConfig.log.info("接口结果验证成功，测试通过");
        //关闭连接
        sqlsession.close();
    }

    private String getResult(loginCase loginCase) throws IOException {
        //创建HTTPPOST连接
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        //准备请求参数
        JSONObject postParm = new JSONObject();
        postParm.put("userName",loginCase.getUserName());
        postParm.put("password",loginCase.getPassword());
        //将参数添加到entity实体中
        StringEntity entity = new StringEntity(postParm.toString(),"utf-8");
        //设置请求头信息
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        //设置参数
        post.setEntity(entity);
        //执行post请求
        HttpResponse httpResponse = TestConfig.closeableHttpClient.execute(post);
        TestConfig.log.info("执行post请求");
        String result = EntityUtils.toString(httpResponse.getEntity());
        TestConfig.log.info("接口返回结果为"+result);
        //遍历cookies，方便判断cookie是否获取到
        List<Cookie> cookies = TestConfig.basicCookieStore.getCookies();
        for (Cookie cookie:cookies) {
            //日志记录cookie信息
            TestConfig.log.info("cookies信息");
            TestConfig.log.info("cookies:"+cookie.getName()+":"+cookie.getValue());
        }


        return result;
    }
}
