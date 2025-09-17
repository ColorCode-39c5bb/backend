package cn.violetgarden.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.violetgarden.blog.dao.Article;
import cn.violetgarden.blog.dao.Profile;
import cn.violetgarden.blog.dao.Setting;
import cn.violetgarden.blog.dao.Tag;
import cn.violetgarden.blog.service.ArticleService;
import cn.violetgarden.blog.service.ProfileService;
import cn.violetgarden.blog.service.SettingService;
import cn.violetgarden.blog.service.TagService;
import cn.violetgarden.blog.service.UserService;

@CrossOrigin
@RestController//已封装了@Controller
public class ControllerManager {

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
    

    @PutMapping(value = "/api/article", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseBody update(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("tags") String tags,
            @RequestParam(value="cover",required=false) String cover,
            @RequestParam(value="coverFile",required=false) MultipartFile coverFile
    ) throws IOException {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setTags(new ObjectMapper().readValue(tags, new TypeReference<List<Tag>>() {}));
        article.setCover(cover);
        article.setCoverFile(coverFile);
        return new ResponseBody(true, "successed", articleService.update(article));
    }

    @PutMapping("/api/profile")
    public ResponseBody updateProfile(@RequestBody Profile profile) {
        System.out.println(profile);
        return new ResponseBody(true, "successed", profileService.update(profile));
    }

    @PutMapping("/api/setting")
    public ResponseBody updateSetting(@RequestBody Setting setting) {
        System.out.println(setting);
        return new ResponseBody(true, "successed", settingService.update(setting));
    }

    @PostMapping(value = "/api/article", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseBody insert(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("tags") String tags,
            @RequestParam(value="coverFile",required=false) MultipartFile coverFile
    ) throws IOException {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setTags(new ObjectMapper().readValue(tags, new TypeReference<List<Tag>>() {}));
        article.setCoverFile(coverFile);
        return new ResponseBody(true, "successed", articleService.insert(article));
    }

    @PostMapping("/api/tags")
    public ResponseBody insert(@RequestBody Tag tag) {
        return new ResponseBody(true, "successed", tagService.insert(tag));
    }
    
    @DeleteMapping("/api/article")
    public ResponseBody delete_article_byId(@RequestBody Article article) {
        return new ResponseBody(true, "successed", articleService.delete(article));
    }

    @DeleteMapping("/api/tags")
    public ResponseBody deleteByIdTags(Integer id) {
        return new ResponseBody(true, "successed", tagService.delete(id));
    }
}
