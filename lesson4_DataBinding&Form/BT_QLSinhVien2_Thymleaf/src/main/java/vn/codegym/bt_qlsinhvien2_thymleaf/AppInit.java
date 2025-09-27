package vn.codegym.bt_qlsinhvien2_thymleaf;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {//nơi khai báo beans dành cho service/data//....
        return new Class<?>[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//nơi khai báo beans dành cho web layer(controller,view,...)
        return new Class<?>[]{WebConfig.class, ThymeleafConfig.class};
    }

    @Override
    protected String[] getServletMappings() {// đường dẫn gốc mappping"/"
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // Cấu hình thông tin file được upload
        // location (lưu vào thư mục trên webserver)
        String location = System.getProperty("java.io.tmpdir");
        // max file size (5Mb)
        long maxFileSize = 5 * 1024 * 1024; // = 5Mb
        // max request size (50Mb)
        long maxRequestSize = 50 * 1024 * 1024;
        // file size threshold ~ Cached ??? = 0 ; ghi ra đĩa
        int  fileSizeThreshold = 0;
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location,  maxFileSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfigElement);
    }
}
