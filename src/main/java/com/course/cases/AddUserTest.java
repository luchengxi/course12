package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.addUserCase;
import com.course.utils.MybatisSqlSession;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {
    @Test(description = "添加用户接口测试")
    public void adduserTes() throws IOException {
        //从数据库中获取测试参数
        SqlSession sqlSession = MybatisSqlSession.getSqlsession();
        addUserCase addUserCase = sqlSession.selectOne("addUserCase", "addUserCase");
        TestConfig.log.info("添加用户信息接口测试地址为"+TestConfig.addUserUrl);
        TestConfig.log.info("用例参数为"+addUserCase.toString());

        //获取实际结果
        JSONObject resultUser = getResultUser(addUserCase);
        //结果比对
        if(resultUser.getString("userName").equals(addUserCase.getUserName())&&resultUser.getString("password").equals(addUserCase.getPassword())){
            TestConfig.log.info("结果验证成功，测试通过");
        }

        sqlSession.close();

    }

    private JSONObject getResultUser(addUserCase addUserCase) throws IOException {
        //创建post请求
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        //准备请求参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",addUserCase.getUserName());
        jsonObject.put("password",addUserCase.getPassword());
        jsonObject.put("age",addUserCase.getAge());
        jsonObject.put("sex",addUserCase.getAge());

        jsonObject.put("permission",addUserCase.getPermission());
        jsonObject.put("isDelete",addUserCase.getIsDelete());
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        post.setEntity(entity);
        //设置请求头信息
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        //执行请求
        TestConfig.log.info("执行post请求");
        HttpResponse response = TestConfig.closeableHttpClient.execute(post);
        //获取执行结果,并转换成json,因为响应数据为json
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        TestConfig.log.info("接口的返回结果为"+result);
        JSONObject resultJson = new JSONObject(result);
        return resultJson;
    }
}
