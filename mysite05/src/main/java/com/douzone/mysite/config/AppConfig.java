package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;
import com.douzone.config.app.MyBatisConfig;

// <context:annotation-config />
// <context:component-scan	base-package="com.douzone.mysite.service, com.douzone.mysite.repository, com.douzone.mysite.aspect">
// 	 <context:include-filter type="annotation" expression="org.springframework.stereotype.Repository" />
//	 <context:include-filter type="annotation" expression="org.springframework.stereotype.Service" />
//	 <context:include-filter type="annotation" expression="org.springframework.stereotype.Component" />
// </context:component-scan>

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.douzone.mysite.service", "com.douzone.mysite.repository", "com.douzone.mysite.aspect" })
@Import({ DBConfig.class, MyBatisConfig.class })
public class AppConfig {
}
