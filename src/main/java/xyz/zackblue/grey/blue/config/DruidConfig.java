package xyz.zackblue.grey.blue.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean ServletRegistrationBean() {

        ServletRegistrationBean<StatViewServlet> Bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        Map<String, String> initParameters=new HashMap<>();
        Bean.setInitParameters(initParameters);//初始化参数
        initParameters.put("loginUsername","admin");//登录key是固定的 loginUsername loginPassword
        initParameters.put("loginPassword","123456");

        initParameters.put("allow","");

        return Bean;
    }
}
