package cn.violetgarden.blog.service;

import cn.violetgarden.blog.dao.Setting;

public interface SettingService{
    public Setting selectById(Long id);
    
    public Integer update(Setting setting);
}