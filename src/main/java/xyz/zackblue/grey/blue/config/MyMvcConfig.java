package xyz.zackblue.grey.blue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login.html").setViewName("/pages/samples/login-2");
        registry.addViewController("/").setViewName("/pages/samples/login-2");
        registry.addViewController("/charts.html").setViewName("/pages/charts/chartjs.html");

        //registry.addViewController("/table.html").setViewName("/pages/tables/basic-table.html");
        registry.addViewController("/elements.html").setViewName("/pages/forms/basic_elements.html");
        registry.addViewController("/icons.html").setViewName("/pages/icons/mdi.html");
        registry.addViewController("/buttons.html").setViewName("/pages/ui-features/buttons.html");
        registry.addViewController("/typography.html").setViewName("/pages/ui-features/typography.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/index.html");
    }
}
