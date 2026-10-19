package cn.violetgarden.blog.dao.mapper.postgresql.association;

import cn.violetgarden.blog.dao.mapper.postgresql.association.AssociationMapper.SQLProvider;

public @interface AssociationSQL {
	SQLProvider value();
}
