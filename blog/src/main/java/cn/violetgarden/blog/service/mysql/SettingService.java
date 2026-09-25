package cn.violetgarden.blog.service.mysql;

import cn.violetgarden.blog.entity.mysql.Setting;

public interface SettingService{
    public Setting selectById(Long id);
    
    public Integer update(Setting setting);

    public Integer increment_visitview();
}