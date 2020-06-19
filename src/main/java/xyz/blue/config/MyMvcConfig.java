package xyz.blue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                .allowedOrigins("*")    //开放哪些ip、端口、域名的访问权限
                .allowCredentials(true)  //是否允许发送Cookie信息
                .allowedMethods("GET", "POST", "PUT", "DELETE")     //开放哪些Http方法，允许跨域访问
                .allowedHeaders("*")     //允许HTTP请求中的携带哪些Header信息
                .exposedHeaders("/*");   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//        registry.addViewController("/dashboard.html").setViewName("index");
////        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/login.html").setViewName("/pages/samples/login-2");
//        registry.addViewController("/").setViewName("/pages/samples/login-2");
//        registry.addViewController("/pages/charts.html").setViewName("/pages/charts/chartjs");
//
//        registry.addViewController("/pages/table.html").setViewName("/pages/tables/basic-table");
//        registry.addViewController("/pages/elements.html").setViewName("/pages/forms/basic_elements");
//        registry.addViewController("/icons.html").setViewName("/pages/icons/mdi");
//        registry.addViewController("/buttons.html").setViewName("/pages/ui-features/buttons");
//        registry.addViewController("/typography.html").setViewName("/pages/ui-features/typography");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/dashboard.html");
//    }
}
