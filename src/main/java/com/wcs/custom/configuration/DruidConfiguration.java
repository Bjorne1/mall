package com.wcs.custom.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.wcs.custom.mybatis.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/8/29 10:05
 */
@Configuration
public class DruidConfiguration {

    @Bean(name = "mall")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mall")
    public DataSource mallDruid() {
        return new DruidDataSource();
    }

    @Bean(name = "sky")
    @ConfigurationProperties(prefix = "spring.datasource.druid.sky")
    public DataSource skyDruid() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource() {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put("mall", mallDruid());
        targetDataSources.put("sky", skyDruid());
        return new DynamicDataSource(mallDruid(), targetDataSources);
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //设置ip黑名单，优先级高于白名单
        servletRegistrationBean.addInitParameter("deny", "192.168.0.19");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "root");
        servletRegistrationBean.addInitParameter("loginPassword", "root");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        //创建过滤器
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> servletRegistrationBean() {
        return new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    }
}