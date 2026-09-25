package cn.violetgarden.blog.servlet;

import java.util.Enumeration;

import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("MyServlet initialized");
		super.init(config);
	}
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = req.getServletContext();
		Enumeration<String> attributeNames = context.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            System.out.println(attributeName);
        }
        // 3. 尝试获取Root Context
        WebApplicationContext rootContext = (WebApplicationContext)
            context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
	
		System.out.println("✅ 通过ROOT_KEY获取到: " + rootContext);
		System.out.println("rootContext beans: ");
		for(String bn : rootContext.getBeanDefinitionNames()) System.out.print(bn + "、");
		// String[] beanNames = rootContext.getBeanDefinitionNames();
		// for (String beanName : beanNames) {
		// 	System.out.println(beanName);
		// }
    }
}

