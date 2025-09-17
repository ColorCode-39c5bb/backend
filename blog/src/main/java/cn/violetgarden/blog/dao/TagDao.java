package cn.violetgarden.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TagDao{

    @Select("""
        SELECT Tag.*, COUNT(Article_Tag.article_id) AS article_count 
        FROM Tag 
            LEFT JOIN Article_Tag ON Tag.id = Article_Tag.tag_id 
        GROUP BY Tag.id 
    """)
    public List<Tag> selectAll();

    @Insert("insert into Tag value(null, #{tagname})")
    public Integer insert(Tag tag);
    @Select("select LAST_INSERT_ID()")
    public Integer get_IdLastInsert();

    @Delete("delete from Article_Tag where tag_id = #{id};delete from Tag where id = #{id};")
    public Integer delete(Integer id);

}