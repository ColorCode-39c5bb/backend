package cn.violetgarden.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.mapper.TagMapper;
import cn.violetgarden.blog.entity.Tag;
import cn.violetgarden.blog.service.TagService;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectAll(){
        return tagMapper.selectAll();
    }

    @Override
    public Integer insert(Tag tag){
        tagMapper.insert(tag);
        return tagMapper.get_IdLastInsert();
    }

    @Override
    public Integer delete(Integer id){
        return tagMapper.delete(id);
    }
}