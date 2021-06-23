package com.zx.daoyundev.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zx.daoyundev.service.SendSmsService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class SendSmsServiceImpl implements SendSmsService {
    private static final Logger logger = LoggerFactory.getLogger(SendSmsServiceImpl.class);
    // 这里采用 注入的方式传递参数
    @Value("${aliyun.accessKeyID}")
    private String accessKeyID;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Override
    public boolean sendSms(String tel, String code) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tSK1XcVyRECEJotx48k", "jDVzBYFEp43EuiUSUzghz4yADZKTyT");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        //request.putQueryParameter("RegionId", "cn-hangzhou");
        //request.putQueryParameter("SignName", "签名名称");
        request.putQueryParameter("SignName", "生意专家");

        request.putQueryParameter("PhoneNumbers", tel);
        //request.putQueryParameter("TemplateCode", "模板code");
        request.putQueryParameter("TemplateCode", "SMS_205882264");

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));

        try {
            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());  // 返回的消息
            logger.info(JSON.parseObject(response.getData(), Map.class).get("Message").toString());
            return response.getHttpResponse().isSuccess();

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }

}
