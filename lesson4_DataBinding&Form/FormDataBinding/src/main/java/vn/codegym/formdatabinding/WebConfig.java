package vn.codegym.formdatabinding;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// webconfig thay thế cho dispatcher_servlet
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "vn.codegym.formdatabinding")//load beans --> controllers là các object(beans)
public class WebConfig implements WebMvcConfigurer {

    // beans dành cho view resolver
    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // đường dẫn đến view ở đâu
        viewResolver.setPrefix("/WEB-INF/views/");
        // đuôi file view lad gì (jsp)
        viewResolver.setSuffix(".jsp");
        // font hiển thị tiếng việt
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

}
