package cn.violetgarden.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao{

    @Select("select * from User where id = #{id}")
    public User selectById(Integer id);

    @Select("select * from User where username = #{username}")
    public User selectByUsername(String username);

    @Select("select * from User")
    public List<User> selectAll();



    @Insert("insert into User(id, username, `password`, age, gender, email) values(#{id}, #{username}, #{password}, #{age}, #{gender}, #{email});")
    public Integer insert(User user);

    @Update("update User set username=#{username}, password=#{password}, age=#{age}, gender=#{gender}, email=#{email} where id=#{id}")
    public Integer UpdateById(User user);



    @Delete("delete from User where username = #{username}")
    public Integer deleteByName(String username);

    @Delete("delete from User where id = #{id}")
    public Integer deleteById(Integer id);

}