package vn.codegym.currencyconverterapp;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {//nơi khai báo beans dành cho service/data//....
        return new Class<?>[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//nơi khai báo beans dành cho web layer(controller,view,...)
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {// đường dẫn gốc mappping"/"
        return new String[]{"/"};
    }
}
