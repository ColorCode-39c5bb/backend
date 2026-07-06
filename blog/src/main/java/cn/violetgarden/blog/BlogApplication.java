package cn.violetgarden.blog;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlogApplication{
public static void main(String[] args) {
	try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {
		String[] beanNames = context.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}
}