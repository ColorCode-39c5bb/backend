package cn.violetgarden.blog.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.controller.request_body.ArticleRequestBody;
import cn.violetgarden.blog.dao.*;
import cn.violetgarden.blog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    
    @Override
    public List<Article> get_article(ArticleRequestBody requestBody) {
        Integer pagesize = requestBody.getPagesize(),
                start = (requestBody.getPage() - 1) * pagesize;

        return articleDao.select_article(
                start, pagesize,
                "%" + requestBody.getKeyword() + "%",
                requestBody.getKeyword().length(),
                requestBody.getTags(),
                requestBody.getTags().size()
        );
    }

    @Override
    public Article selectById(Long id) {
        return articleDao.selectById(id);
    }

    @Override
    public Integer update_read_byId(Integer article_id) {
        return articleDao.update_read_byId(article_id);
    }

    @Override
    public Long insert(Article article) {
        articleDao.insert(article);
        Long idLastInsert = articleDao.get_IdLastInsert();
        article.setId(idLastInsert);
        try {
            update(article);
        } catch (IOException ex) {
            System.err.println("Error updating article after insert: " + ex.getMessage());
        }
        return idLastInsert;
    }

    @Override
    public Integer update(Article article) throws IOException {
        if (article.getCoverFile() != null) {
            delete_cover(article);//不论是否有旧封面，都删除
            String originalFilename = article.getCoverFile().getOriginalFilename(),
                    suffix = originalFilename.substring(originalFilename.lastIndexOf(".")),
                    filename = "cover_" + article.getId() + suffix;
            article.getCoverFile().transferTo(new File(new ClassPathResource("static").getFile().getAbsolutePath() + "/" + filename));
            article.setCover(filename);
        }
        articleDao.update(article);
        return articleDao.update_ArticleTag_byId(article.getId(), article.getTags());
    }

    @Override
    public Integer delete(Article article) {
        delete_cover(article);
        return articleDao.deleteById(article.getId());
    }

    private boolean delete_cover(Article article) {
        System.out.println(article);
        try {
            File file = new File(new ClassPathResource("static").getFile().getAbsolutePath() + "/" + article.getCover());
            return file.delete();
        } catch (IOException ex) {
            System.err.println("Error deleting cover file: " + ex.getMessage());
            return false;
        }
    }
}
