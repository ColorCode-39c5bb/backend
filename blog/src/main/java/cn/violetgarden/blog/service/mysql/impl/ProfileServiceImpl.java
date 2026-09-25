package cn.violetgarden.blog.service.mysql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.mapper.mysql.ProfileMapper;
import cn.violetgarden.blog.entity.mysql.Profile;
import cn.violetgarden.blog.service.mysql.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public Profile selectById(Long id){
        return profileMapper.selectById(id);
    }
    
    @Override
    public Integer update(Profile Profile){
        return profileMapper.update(Profile);
    }
}