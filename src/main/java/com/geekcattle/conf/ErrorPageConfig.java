
package com.geekcattle.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


@Configuration
public class ErrorPageConfig {

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new MyCustomizer();
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
        	//其他错误
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
            //500返回首页
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/"));
            //404跳到404页面
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            
        }

    }
}
