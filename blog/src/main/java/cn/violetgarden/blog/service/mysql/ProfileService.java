package cn.violetgarden.blog.service.mysql;

import cn.violetgarden.blog.entity.mysql.Profile;

public interface ProfileService{

    public Profile selectById(Long id);

    public Integer update(Profile Profile);

}