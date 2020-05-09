package xyz.blue.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean
            (@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterMap=new LinkedHashMap<>();
        /*
        * anon：无需认证访问
        * authc：必须认证访问
        * user：必须拥有 记住我 功能访问
        * perms：拥有对某资源的权限
        * role：拥有某个角色权限
        * */
        filterMap.put("/pages/charts/**","perms[user:elements]");
        //filterMap.put("/**", "authc");

        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterMap.put("/logout", "logout");

        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/");//拦截未登录，跳转页面
        bean.setUnauthorizedUrl("/noach");//未授权页面
        bean.setSuccessUrl("/index");
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {

        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        SecurityManager.setRealm(userRealm);
        return SecurityManager;
    }

    @Bean
    public UserRealm userRealm() {

        return new UserRealm();
    }
}
