package cn.violetgarden.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.*;
import cn.violetgarden.blog.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService{

    @Autowired
    private SettingDao settingDao;

    @Override
    public Setting selectById(Long id){
        return settingDao.selectById(id);
    }
    
    @Override
    public Integer update(Setting setting){
        return settingDao.update(setting);
    }
}