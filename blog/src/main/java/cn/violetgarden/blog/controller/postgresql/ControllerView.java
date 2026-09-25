package cn.violetgarden.blog.controller.postgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.violetgarden.blog.entity.postgresql.Article;
import cn.violetgarden.blog.service.postgresql.ArticleService;

@CrossOrigin
@RestController//已封装了@Controller
public class ControllerView {
	@Autowired
	private ArticleService articleService;

	@GetMapping("/article")
	public Article get_article(){
		return articleService.get_article(2L);
	}
}
