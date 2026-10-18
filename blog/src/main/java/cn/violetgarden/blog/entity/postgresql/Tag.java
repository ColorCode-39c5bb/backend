package cn.violetgarden.blog.entity.postgresql;

import cn.violetgarden.blog.dao.mapper.postgresql.association.PrimaryKey;

public class Tag {
    @PrimaryKey("name") private String name;
	public Tag(String name) {
		this.name = name;
	}

	@Override
	public String toString(){ return String.format(
"""
Tag{
	name='%s'
}
"""
	, name);}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
