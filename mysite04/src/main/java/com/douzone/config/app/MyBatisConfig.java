package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

//	03의 applicationContext.xml의 부분과 같음
//	<!-- MyBatis SqlSessionFactoryBean -->
//	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//		<property name="dataSource" ref="dataSource" />
//		<property name="configLocation" value="classpath:mybatis/configuration.xml" />
//	</bean>

	@Bean // 이게 있으면 컨테이너가 bean 생성 메시지임을 알아볼 수 있지 ㅇㅋ?
	public SqlSessionFactory sqlSessionFactory(DataSource datasource, ApplicationContext applicationContext)
			throws Exception {

		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource);
		// 리소스 형태로 받기 때문에, String을 바꿔줄 필요가 있다.
		sqlSessionFactory.setConfigLocation(
				applicationContext.getResource("classpath:com/douzone/mysite/config/app/mybatis/configuration.xml"));

		return sqlSessionFactory.getObject();
	}

//	<!-- MyBatis SqlSessionTemplate -->
//	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
//		<constructor-arg index="0" ref="sqlSessionFactory" />
//	</bean>

	@Bean
	public SqlSession sqlsession(SqlSessionFactory sqlSessionFactory) {

		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
