package com.zx.daoyundev.security.jwt;

import com.alibaba.fastjson.JSON;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zx
 * @date 2021/6/29 17:21
 */
//1.用户登陆之后，使用密码对账号进行签名生成并返回token并设置过期时间；
//
//        2.将token保存到本地，并且每次发送请求时都在header上携带token。
//
//        3.shiro过滤器拦截到请求并获取header中的token，并提交到自定义realm的doGetAuthenticationInfo方法。
//
//        4.通过jwt解码获取token中的用户名，从数据库中查询到密码之后根据密码生成jwt效验器并对token进行验证。
//
//        通俗点来说，就是服务器根据给定的密钥和算法，对用户名或者ID之类（只要是能判断是某一个用户的唯一标识都行）加上过期时间的时间戳进行加密，然后生成类似XXX.XXX.XXX的字符串，
//        这个字符串就是所谓的token，以后要想访问服务器得到资源，只需要在请求头带上token，服务器拿到这个token，再进行解密验证操作，判断该用户是否是有效用户，然后放行。
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断用户是否想要登录。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        System.out.println(authorization);
        return authorization != null;
    }

    /**
     * 如果用户想登录，执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，委托ShiroManager执行，如果错误他会抛出异常并被捕获
        Subject subject = getSubject(request, response);
        subject.login(token);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) { // 如果token不为空就执行登录操作
            try {
                executeLogin(request, response); // 登录抛出异常说明登录失败
            } catch (Exception e) {
                return false;
            }
        }
        return true; // 登录成功
    }

    /*
        验证失败时的处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        String result = JSON.toJSONString(Result.failure(ResultCodeEnum.TokenError));
        httpServletResponse.getWriter().println(result);
        return false;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            LOGGER.error(() -> e.getMessage());
        }
    }
}
