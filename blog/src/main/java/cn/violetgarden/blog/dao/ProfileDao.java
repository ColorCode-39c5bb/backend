package cn.violetgarden.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProfileDao{

    @Select("select * from Profile where id=#{id}")
    public Profile selectById(Long id);

    @Update("""
    <script>
        update Profile 
        <set>
            <if test="signatures != null">signatures = #{signatures},</if>
            <if test="nick != null">nick = #{nick},</if>
            <if test="profile != null">profile = #{profile},</if>
            <if test="about != null">about = #{about},</if>
        </set>
        where id = #{id}
    </script>
    """)
    public Integer update(Profile profile);
}