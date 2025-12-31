package cn.violetgarden.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SettingDao{

    @Select("select * from Setting where id=#{id}")
    public Setting selectById(Long id);

    @Update("""
    <script>
        update Setting 
        <set>
            <if test="pagesize != null">pagesize = #{pagesize},</if>
            <if test="visitview != null">visitview = #{visitview},</if>
        </set>
        where id = #{id}
    </script>
    """)
    public Integer update(Setting setting);

    @Update("UPDATE Setting SET `visitview` = `visitview`+1 WHERE id=1")
    public Integer increment_visitview();
}