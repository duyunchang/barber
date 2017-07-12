
package com.geekcattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc // 启动MVC
@EnableTransactionManagement // 启注解事务管理
@SpringBootApplication // SpringBoot启动核心
public class Application extends SpringBootServletInitializer {

	/**
	 * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
	 * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(Application.class, args);
		System.out.println("========================================start========================================");

	}

}
