package com.zx.daoyundev.security.shiro;

import com.zx.daoyundev.entity.Role;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.security.jwt.JWTToken;
import com.zx.daoyundev.security.jwt.JWTUtil;
import com.zx.daoyundev.service.RoleService;
import com.zx.daoyundev.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zx
 * @date 2021/6/29 17:08
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    /*
     * 安全数据源，ShiroManager 从这里获取安全数据，就是角色以及权限
     */

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*
        判断此Realm是否支持token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }



    /*
        用户权限信息,只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        User user = userService.findByTel(subject.getPrincipal().toString());
        Role userRole = roleService.getRoleById(user.getRole());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(userRole.getRoleName());
        Set<String> permission = new HashSet<>(Arrays.asList(userRole.getPerms().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);

        return simpleAuthorizationInfo;
    }

    /*
     *  根据token获取身份认证信息
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得tel，用于和数据库中的用户数据进行对比
        String tel = JWTUtil.getUsertel(token);
//        System.out.println("Realm中的token: " + token);
        if (tel == null) {
            System.out.println("身份认证失败，手机号为空");
            throw new AuthenticationException("token invalid");
        }

        User user = userService.findByTel(tel);
        if (user == null) { // 用户不存在
            throw new AuthenticationException("User didn't existed!");
        }
        // 如果token有效，那么会判断用户名与密码是否正确
        if (!JWTUtil.verify(token, tel, user.getUserPassward())) { // 密码错误
            throw new AuthenticationException("token 已过期或用户名密码错误，请重新登录！");
        }
        return new SimpleAuthenticationInfo(tel, token, "shiro_realm");
    }
}

