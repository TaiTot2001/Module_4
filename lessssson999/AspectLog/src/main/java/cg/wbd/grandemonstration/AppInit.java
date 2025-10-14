package cg.wbd.grandemonstration;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // không có RootConfig
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class}; // Đăng ký AppConfig
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // DispatcherServlet bắt toàn bộ request
    }
}
