package vn.codegym.bt_qlsinhvien2_thymleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false); // tối ưu quá trình biên dịch template

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
         templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver getViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

        // Gán template engine
        viewResolver.setTemplateEngine(templateEngine());

        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"*"}); // tìm .html --> Views
        return viewResolver;
    }
}