package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
	
		// 03 applicationContext.xml의 아래와 같은 것
		//	<bean id="dataSource"
		//		class="org.apache.commons.dbcp.BasicDataSource">
		//		<property name="driverClassName" value="org.mariadb.jdbc.Driver" />
		//		<property name="url" value="jdbc:mysql://127.0.0.1:3307/webdb?characterEncoding=utf8" />
		//		<property name="username" value="webdb" />
		//		<property name="password" value="webdb" />
		//	</bean>
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3307/webdb?characterEncoding=utf8");
		dataSource.setUsername("webdb");
		dataSource.setPassword("webdb");
		
		// 미리 연결해 두는 것이 100개
		dataSource.setInitialSize(10);
		// 
		dataSource.setMaxActive(20);
		return dataSource;
	}
	
	

}
