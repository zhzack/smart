package xyz.blue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//        registry.addViewController("/dashborad.html").setViewName("index");
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
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/dashborad.html");
//    }
}
