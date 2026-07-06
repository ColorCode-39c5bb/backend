package cn.violetgarden.blog.service;

import cn.violetgarden.blog.entity.Profile;

public interface ProfileService{

    public Profile selectById(Long id);

    public Integer update(Profile Profile);

}