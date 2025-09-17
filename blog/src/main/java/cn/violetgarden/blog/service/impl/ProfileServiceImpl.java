package cn.violetgarden.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.*;
import cn.violetgarden.blog.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileDao profileDao;

    @Override
    public Profile selectById(Long id){
        return profileDao.selectById(id);
    }
    
    @Override
    public Integer update(Profile Profile){
        return profileDao.update(Profile);
    }
}