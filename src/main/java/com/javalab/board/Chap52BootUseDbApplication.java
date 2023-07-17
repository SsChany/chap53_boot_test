package com.javalab.board;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Chap52BootUseDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap52BootUseDbApplication.class, args);
	}

	// MyBatis를 App전체에서 활용할 수 있도록 Application Class에서 초기화
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//      sessionFactory.setDataSource(dataSource);
//      
//      Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");
//      sessionFactory.setMapperLocations(res);
//      sessionFactory.setTypeAliasesPackage("com.test.dto"); //여기 추가
//      
//      return sessionFactory.getObject();
//  }	
	
}
