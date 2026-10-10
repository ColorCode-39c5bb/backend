package cn.violetgarden.blog.controller.postgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.violetgarden.blog.controller.request_body.ArticleRequestBody;
import cn.violetgarden.blog.entity.postgresql.Account;
import cn.violetgarden.blog.entity.postgresql.Article;
import cn.violetgarden.blog.entity.postgresql.Tag;
import cn.violetgarden.blog.service.postgresql.TagService;
import cn.violetgarden.blog.service.postgresql.AccountService;
import cn.violetgarden.blog.service.postgresql.ArticleService;

@CrossOrigin
@RestController//已封装了@Controller
public class ControllerView {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TagService tagService;

	@GetMapping("/account")
	public Account get_account(String number){
		return accountService.get_account(number);
	}

	@GetMapping("/article")
	public Article get_article(Long id){
		return articleService.get_article(id);
	}

	@PutMapping("/articles")
	public Article[] put_articles(@RequestBody ArticleRequestBody arb){
		return articleService.get_articles(arb);
	}

	@GetMapping("/tags")
	public Tag[] get_tags(){
		return tagService.get_tags();
	}
}
