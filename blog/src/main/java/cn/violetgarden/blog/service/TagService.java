package cn.violetgarden.blog.service;

import java.util.List;

import cn.violetgarden.blog.entity.Tag;

public interface TagService{
    public List<Tag> selectAll();

    public Integer insert(Tag tag);

    public Integer delete(Integer id);

}