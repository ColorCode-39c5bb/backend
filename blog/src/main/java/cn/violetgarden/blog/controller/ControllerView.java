package cn.violetgarden.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.violetgarden.blog.controller.request_body.ArticleRequestBody;
import cn.violetgarden.blog.controller.request_body.LoginRequestBody;
import cn.violetgarden.blog.dao.Article;
import cn.violetgarden.blog.service.ArticleService;
import cn.violetgarden.blog.service.ProfileService;
import cn.violetgarden.blog.service.SettingService;
import cn.violetgarden.blog.service.TagService;
import cn.violetgarden.blog.service.UserService;

@CrossOrigin
@RestController//已封装了@Controller
public class ControllerView {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private SettingService settingService;

    @PostMapping("/articles")
    public ResponseBody get_article(@RequestBody ArticleRequestBody articleRequestBody) {
        System.out.println(articleRequestBody);
        List<Article> articles = articleService.get_article(articleRequestBody);
        return new ResponseBody(true, "successed", articles, articles.isEmpty() ? 0 : articles.get(0).getItemtotal());
    }

    @GetMapping("/article")
    public ResponseBody selectById_Article(Long id) {
        return new ResponseBody(true, "successed", articleService.selectById(id));
    }

    @GetMapping("/profile")
    public ResponseBody getProfile(Long id) {
        System.out.println("config");
        return new ResponseBody(true, "successed", profileService.selectById(id));
    }
    @GetMapping("/setting")
    public ResponseBody getSetting(Long id) {
        System.out.println("config");
        return new ResponseBody(true, "successed", settingService.selectById(id));
    }

    @GetMapping("/tags")
    public ResponseBody selectAllTag() {
        System.out.println("tags");
        return new ResponseBody(true, "successed", tagService.selectAll());
    }

    @PutMapping("/read")
    public ResponseBody read(Integer id) {
        System.out.println("read");
        return new ResponseBody(true, "successed", articleService.update_read_byId(id));
    }



    @PostMapping("/login")
    public ResponseBody login(@RequestBody LoginRequestBody loginRequestBody) {
        System.out.println(loginRequestBody);
        String token = userService.login(loginRequestBody);
        if (token != null) {
            return new ResponseBody(true, "successed", token);
        } else {
            return new ResponseBody(false, "登录失败", null);
        }
    }

}
