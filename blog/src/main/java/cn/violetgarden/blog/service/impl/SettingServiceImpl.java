package cn.violetgarden.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.mapper.SettingMapper;
import cn.violetgarden.blog.entity.Setting;
import cn.violetgarden.blog.service.SettingService;

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