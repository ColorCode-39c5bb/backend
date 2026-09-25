package cn.violetgarden.blog.service.mysql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.mapper.mysql.SettingMapper;
import cn.violetgarden.blog.entity.mysql.Setting;
import cn.violetgarden.blog.service.mysql.SettingService;

@Service
public class SettingServiceImpl implements SettingService{

    @Autowired
    private SettingMapper settingMapper;

    @Override
    public Setting selectById(Long id){
        return settingMapper.selectById(id);
    }
    
    @Override
    public Integer update(Setting setting){
        return settingMapper.update(setting);
    }

    @Override
    public Integer increment_visitview() {
        return settingMapper.increment_visitview();
    }
    
    
}