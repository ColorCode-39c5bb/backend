package cn.violetgarden.blog.dao.mapper.postgresql.association;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
	String value();
}
