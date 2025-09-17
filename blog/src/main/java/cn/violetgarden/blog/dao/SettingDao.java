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
        </set>
        where id = #{id}
    </script>
    """)
    public Integer update(Setting setting);
}