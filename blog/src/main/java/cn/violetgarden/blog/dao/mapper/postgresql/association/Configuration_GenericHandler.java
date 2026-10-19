package cn.violetgarden.blog.dao.mapper.postgresql.association;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

public class Configuration_GenericHandler extends Configuration{
public Configuration_GenericHandler() {super();}
public Configuration_GenericHandler(Environment environment) {super(environment);}
@Override public <T> void addMapper(Class<T> mapper) {
	super.addMapper(mapper);
	for(Type type : mapper.getGenericInterfaces()){ if(type == AssociationMapper.class){
		System.out.println(mapper.getName());
		Method[] methods = Stream.of(AssociationMapper.class.getMethods()).filter(m->m.getAnnotation(AssociationSQL.class)!=null).toArray(Method[]::new);
		SqlSource[] sqlsources = Stream.of(methods).map(method->this.getDefaultScriptingLanguageInstance().createSqlSource(this, method.getAnnotation(AssociationSQL.class).value().getSQL((Class<?>[])((ParameterizedType)type).getActualTypeArguments()), null)).toArray(SqlSource[]::new);
		for(short i=0;i<sqlsources.length;i++) this.addMappedStatement(new MappedStatement.Builder(this, mapper.getName()+methods[i].getName(), sqlsources[i], SqlCommandType.SELECT).build());
		break;
	}}
}

static class MapperAnnotationBuilder_GenericHandler extends MapperAnnotationBuilder{
MapperAnnotationBuilder_GenericHandler(Configuration configuration, Class<?> type) {super(configuration, type);}
@Override public void parse() {
	// TODO Auto-generated method stub
	super.parse();
}
}
}
