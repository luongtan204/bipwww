package edu.iuh.fit.nguyenhuusang_tuan7;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: ServletInitializer
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class ServletInitializer  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}