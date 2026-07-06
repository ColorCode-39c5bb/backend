package cn.violetgarden.blog.service;

import cn.violetgarden.blog.entity.Setting;

public interface SettingService{
    public Setting selectById(Long id);
    
    public Integer update(Setting setting);

    public Integer increment_visitview();
}