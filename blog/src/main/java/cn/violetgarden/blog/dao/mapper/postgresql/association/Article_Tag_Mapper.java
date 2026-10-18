package cn.violetgarden.blog.dao.mapper.postgresql.association;

import cn.violetgarden.blog.entity.postgresql.Article;
import cn.violetgarden.blog.entity.postgresql.Tag;
import cn.violetgarden.blog.entity.postgresql.association.Article_Tag;
public interface Article_Tag_Mapper extends AssociationMapper<Article, Tag, Article_Tag>{

}
