package com.zzp.travel.stage.config;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageHelper;

/**
 * 注册MyBatis分页插件PageHelper
 * <p>
 *  //TODO
 *  MybatisConf.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/6/25 21:36
 * @see  MybatisConfig
 **/
@Slf4j
@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper() {
        log.info("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        //配置mysql数据库的方言
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}