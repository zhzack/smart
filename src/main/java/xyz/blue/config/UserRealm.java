package xyz.blue.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.blue.pojo.User;
import xyz.blue.service.UserService;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");

        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;

        User user = userService.selectByName(passwordToken.getUsername());
        //System.out.println(user.toString());
        if (user == null) {

            return null;//UnknownAccountException
        }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);


        //System.out.println(passwordToken.toString());
        return new SimpleAuthenticationInfo(user, user.getUser_pwd(), "");
    }
}
