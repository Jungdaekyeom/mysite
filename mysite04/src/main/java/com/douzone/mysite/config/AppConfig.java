package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;


// <context:annotation-config />
// <context:component-scan	base-package="com.douzone.mysite.service, com.douzone.mysite.repository, com.douzone.mysite.aspect">
// 	 <context:include-filter type="annotation" expression="org.springframework.stereotype.Repository" />
//	 <context:include-filter type="annotation" expression="org.springframework.stereotype.Service" />
//	 <context:include-filter type="annotation" expression="org.springframework.stereotype.Component" />
// </context:component-scan>

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"org.springframework.stereotype.Repository", "org.springframework.stereotype.Service", "org.springframework.stereotype.Component"})
@Import({ DBConfig.class }) // 설정한 DBConfig를 땡겨옴
public class AppConfig {

}
