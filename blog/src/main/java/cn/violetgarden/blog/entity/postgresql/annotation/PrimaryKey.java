package cn.violetgarden.blog.entity.postgresql.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
	String value();
}
