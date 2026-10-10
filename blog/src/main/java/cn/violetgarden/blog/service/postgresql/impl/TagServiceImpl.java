package cn.violetgarden.blog.service.postgresql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.mapper.postgresql.TagMapper;
import cn.violetgarden.blog.entity.postgresql.Tag;
import cn.violetgarden.blog.service.postgresql.TagService;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagMapper tagMapper;
	@Override
	public Tag[] get_tags() {
		return tagMapper.select_all();
	}
}
