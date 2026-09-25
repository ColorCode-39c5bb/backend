package cn.violetgarden.blog.service.mysql;

import java.util.List;

import cn.violetgarden.blog.entity.mysql.Tag;

public interface TagService{
    public List<Tag> selectAll();

    public Integer insert(Tag tag);

    public Integer delete(Integer id);

}