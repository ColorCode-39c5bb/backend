package cn.violetgarden.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper//自动创建实现类
public interface ArticleDao {
    // @Select("select FOUND_ROWS()")
    // public Integer select_total();

    @Results(id = "articleResultMap", value = {
        @Result(property = "id", column = "id"),
        @Result(
                property = "tags",
                column = "id", // 将 article_id 传递给子查询
                javaType = List.class,
                many = @Many(select = "cn.violetgarden.blog.dao.ArticleDao.select_tags_byArticleId")
        )
    })
    @Select("SELECT * FROM Article WHERE id = #{article_id}")
    public Article selectById(Long article_id);

    @Select("""
        SELECT Tag.*
        FROM Article 
        inner JOIN Article_Tag 	ON 	Article_Tag.article_id = Article.id 
        LEFT JOIN Tag 			ON 	Article_Tag.tag_id = Tag.id 
        where Article.id = #{id};
    """)
    public List<Tag> select_tags_byArticleId(Integer id);
    //Article和ArticleCard的Tag不需要展示tag.article_count，所以article_count由TagDao处理

    @Select("""
    <script>
        select SQL_CALC_FOUND_ROWS Article.*, COUNT(*) OVER() as itemtotal
        from Article
        <if test="tagsLength>0">
            left JOIN Article_Tag 	ON 	Article_Tag.article_id = Article.id
            LEFT JOIN Tag 			ON 	Article_Tag.tag_id = Tag.id
        </if>
        <where>
            <if test="tagsLength>0">
                Tag.id IN 
                <foreach item='tag' collection='tags' open='(' separator=',' close=')'>
                    #{tag.id}
                </foreach>
                GROUP BY Article.id HAVING COUNT(DISTINCT Tag.id) = #{tagsLength}
            </if>
            <if test="keywordLength>0">
                and Article.content like #{keyword}
            </if>
        </where>
        order by Article.id
        limit #{start}, #{pagesize};
    </script>
    """)
    @Options(flushCache = Options.FlushCachePolicy.TRUE) // 保持连接
    @ResultMap("articleResultMap")  // 引用公共映射
    public List<Article> select_article(
            Integer start, Integer pagesize,
            String keyword, Integer keywordLength,
            List<Tag> tags, Integer tagsLength
    );

    @Insert("insert into Article value(null, null, null, null, 0, 0, now(), now())")
    public Integer insert(Article article);

    @Select("select LAST_INSERT_ID()")
    public Long get_IdLastInsert();

    @Update("UPDATE Article SET `read` = `read`+1 WHERE id=#{id}")
    public Integer update_read_byId(Integer id);

    @Update("""
    <script>
        update Article 
        <set>
            <if test="cover != null">cover = #{cover},</if>
            <if test="title != null">title = #{title},</if>
            <if test="content != null">content = #{content},</if>
            update_date=now()
        </set>
        where id=#{id}
    </script>
    """)
    public Integer update(Article article);

    public Integer update_ArticleTag_byId(Long id, List<Tag> tags);

    @Delete("""
        delete from Article_Tag where article_id = #{id};
        delete from Article where id = #{id};
    """)
    public Integer deleteById(Long id);
}
