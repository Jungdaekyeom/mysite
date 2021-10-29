package com.douzone.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// web.xml에서 하던 AppConfig를 여기서 처리
	
//	<context-param>
//		<param-name>contextClass</param-name>
//		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
//	</context-param>
//	<context-param>
//		<param-name>contextConfigLocation</param-name>
//		<param-value>com.douzone.mysite.config.AppConfig</param-value>
//	</context-param>
//	
//	<!-- ContextLoad Listener -->
//	<listener>
//		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
//	</listener>
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}

	// web.xml에서 하던 WppConfig를 여기서 처리
	
//	<!-- welcome(default) 페이지 -->
//	<welcome-file-list>
//		<welcome-file>index.html</welcome-file>
//		<welcome-file>index.htm</welcome-file>
//		<welcome-file>index.jsp</welcome-file>
//		<welcome-file>default.html</welcome-file>
//		<welcome-file>default.htm</welcome-file>
//		<welcome-file>default.jsp</welcome-file>
//	</welcome-file-list>
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}
	
	// web.xml에서 하던 이 부분을, 여기서 해결

//	<servlet-mapping>
//		<servlet-name>spring</servlet-name>
//		<url-pattern>/</url-pattern>
//	</servlet-mapping>	

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}


	// web.xml에서 하던 이 부분을, 여기서 해결
	
//	<!-- Encoding Filter -->
//	<filter>
//		<filter-name>encodingFilter</filter-name>
//		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
//		<init-param>
//			<param-name>encoding</param-name>
//			<param-value>UTF-8</param-value>
//		</init-param>
//	</filter>
//	<filter-mapping>
//		<filter-name>encodingFilter</filter-name>
//		<url-pattern>/*</url-pattern>
//	</filter-mapping>
	
	// 필터 등록
	// 양키애들은 필요없지만, 우린 해야함 ㅇㅋ?
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8", false) };
	}
}
