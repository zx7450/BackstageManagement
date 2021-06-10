package com.zx.daoyundev.provider;

import com.alibaba.fastjson.JSON;
import com.zx.daoyundev.entity.AccessTokenDTO;
import com.zx.daoyundev.entity.GithubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.http.MediaType;

@Component
public class GitHubProvider {

    @Autowired
    private RestTemplate restTemplate;
    //GET方式获得User
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+ accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);//将string解析成GitHub对象
            return githubUser;
        } catch (IOException e) {
            System.out.println("getUser"+e);
            return null;
        }

    }

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            //拆分获得需要的access_token值
            String[] split = string.split("&");
            String token = split[0].split("=")[1];
            System.out.println(token);
            return token;
            //return string;
        } catch (IOException e) {
            System.out.println("getAcessToken:" + e);;
        }
        return null;
    }


    /**
     * 生成post请求的JSON请求参数
     * 请求示例:
     * {
     * "id":1,
     * "name":"张耀烽"
     * }
     *
     * @return
     */
//    public HttpEntity<Map<String, String>> generatePostJson(Map<String, String> jsonMap) {
//
//        //如果需要其它的请求头信息、都可以在这里追加
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
//
//        httpHeaders.setContentType(type);
//
//        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(jsonMap, httpHeaders);
//
//        return httpEntity;
//    }


    /**
     * post请求、请求参数为json
     *
     * @return
     */
//
//    public String sendPost(AccessTokenDTO accessTokenDTO) {
//        String uri = "https://github.com/login/oauth/access_token";
//
//        Map<String, String> jsonMap = new HashMap<>(6);
//        jsonMap.put("code", code);accessTokenDTO
//        jsonMap.put("code", code);
//        jsonMap.put("code", code);
//
//        ResponseEntity<String> apiResponse = restTemplate.postForEntity
//                (
//                        uri,
//                        generatePostJson(jsonMap),
//                        String.class
//                );
//        return apiResponse.getBody();
//    }

}
