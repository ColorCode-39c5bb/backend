package cn.violetgarden.blog.servlet;

import java.util.Enumeration;

import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = req.getServletContext();
		Enumeration<String> attributeNames = context.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            System.out.println(attributeName);
        }
        String ROOT_KEY = 
            "org.springframework.web.context.WebApplicationContext.ROOT";
        // 3. 尝试获取Root Context
        WebApplicationContext rootContext = (WebApplicationContext)
            context.getAttribute(ROOT_KEY);
	
		System.out.println("✅ 通过ROOT_KEY获取到: " + rootContext);
		// String[] beanNames = rootContext.getBeanDefinitionNames();
		// for (String beanName : beanNames) {
		// 	System.out.println(beanName);
		// }
    }
}
