package cn.violetgarden.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.*;
import cn.violetgarden.blog.service.TagService;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> selectAll(){
        return tagDao.selectAll();
    }

    @Override
    public Integer insert(Tag tag){
        tagDao.insert(tag);
        return tagDao.get_IdLastInsert();
    }

    @Override
    public Integer delete(Integer id){
        return tagDao.delete(id);
    }
}