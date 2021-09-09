package cz.fel.cvut.koubadom.onto.application;

import cz.fel.cvut.koubadom.onto.application.config.security.CORSfilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KnowledgeSearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(KnowledgeSearchApplication.class, args);

    }


    @Bean
    public FilterRegistrationBean<CORSfilter> corsFilterRegistration() {
        FilterRegistrationBean<CORSfilter> registrationBean =
                new FilterRegistrationBean<>(new CORSfilter());
        registrationBean.setName("CORS Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }


}
