package com.zx.daoyundev;


import com.zx.daoyundev.entity.Initsign;
import com.zx.daoyundev.mapper.InitsignMapper;
import com.zx.daoyundev.mapper.StudentsignMapper;
import com.zx.daoyundev.service.InitsignService;
import com.zx.daoyundev.util.DistanceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class DaoyundevApplicationTests {
//    @Autowired
//    InitsignMapper initsignMapper;
//    @Autowired
//    StudentsignMapper studentsignMapper;
//    @Autowired
//    DistanceUtil distanceUtil;
//    @Autowired
//    DistanceUtil distanceUtil;

    @Test
    void contextLoads() {
    }
//    @Test
//    void getdistance(){
//        System.out.println("经纬度距离计算结果：" + distanceUtil.getDistance(109.371319, 22.155406, 108.009758, 21.679011)+ "米");
//    }
//    @Test
//    void deletesignByteachersignId() {
//        studentsignMapper.deletesignByteachersignId(5);
//    }
//    @Test
//    void gethistorybyCourseid() {
//        List<Initsign> initsignList = initsignMapper.gethistorybyCourseid(100000);
//        for (int i = 0; i < initsignList.size(); i++) {
//            System.out.println(initsignList.get(i));
//        }
//    }
    //void sendSms() {
//
//        // 指定地域名称 短信API的就是 cn-hangzhou 不能改变  后边填写您的  accessKey 和 accessKey Secret
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tSK1XcVyRECEJotx48k", "jDVzBYFEp43EuiUSUzghz4yADZKTyT");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        // 创建通用的请求对象
//        CommonRequest request = new CommonRequest();
//        // 指定请求方式
//        request.setMethod(MethodType.POST);
//        // 短信api的请求地址  固定
//        request.setDomain("dysmsapi.aliyuncs.com");
//        // 签名算法版本  固定
//        request.setVersion("2017-05-25");
//        //请求 API 的名称。
//        request.setAction("SendSms");
//        // 上边已经指定过了 这里不用再指定地域名称
////        request.putQueryParameter("RegionId", "cn-hangzhou");
//        // 您的申请签名
//        request.putQueryParameter("SignName", "生意专家");
//        // 您申请的模板 code
//        request.putQueryParameter("TemplateCode", "SMS_205882264");
//        // 要给哪个手机号发送短信  指定手机号
//        request.putQueryParameter("PhoneNumbers", "13348261944");
//
//        // 创建参数集合
//        Map<String, Object> params = new HashMap<>();
//        // 生成短信的验证码
//        String code = String.valueOf(Math.random()).substring(3, 9);
//        // 这里的key就是短信模板中的 ${xxxx}
//        params.put("code", code);
//
//        // 放入参数  需要把 map转换为json格式  使用fastJson进行转换
//        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));
//
//        try {
//            // 发送请求 获得响应体
//            CommonResponse response = client.getCommonResponse(request);
//            // 打印响应体数据
//            System.out.println(response.getData());
//            // 打印 请求状态 是否成功
//            System.out.println(response.getHttpResponse().isSuccess());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
}
