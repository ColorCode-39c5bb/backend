package cn.violetgarden.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@ServletComponentScan
@SpringBootApplication
public class BlogApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 指定原先用main方法启动的Application类
        return application.sources(BlogApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
