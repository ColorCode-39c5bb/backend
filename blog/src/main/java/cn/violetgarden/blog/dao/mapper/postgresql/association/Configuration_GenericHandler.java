package cn.violetgarden.blog.dao.mapper.postgresql.association;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

public class Configuration_GenericHandler extends Configuration{

@Override
public <T> void addMapper(Class<T> mapper) {
	super.addMapper(mapper);
	for(Type t : mapper.getGenericInterfaces()){ if(t == AssociationMapper.class){
		System.out.println(mapper.getName());
		ParameterizedType pt = (ParameterizedType)t;
		Method[] methods = Stream.of(AssociationMapper.class.getMethods()).filter(m->m.getAnnotation(AssociationSQL.class)!=null).toArray(Method[]::new);
		String[] primarykeys = Stream.of(pt.getActualTypeArguments()).map(c->((Class<?>)c).getSimpleName()).toArray(String[]::new);
		String[] keys = Stream.of(mapper.getFields()).map(k->k.getName()).toArray(String[]::new);
		String[] by0 = {"\""+mapper.getSimpleName()+"\"", "\""+primarykeys[0]+"\"", "\""+primarykeys[0]+"\"."+primarykeys[1], "\""+mapper.getSimpleName()+"\"."+keys[1], keys[0], keys[1]}, by1 = {"\""+mapper.getSimpleName()+"\"", "\""+primarykeys[0]+"\"", "\""+primarykeys[0]+"\"."+primarykeys[1], "\""+mapper.getSimpleName()+"\"."+keys[0], keys[1], keys[0]};
		SqlSource[] sqlsources = Stream.of(methods).map(method->{
			AssociationSQL associationSQL = method.getAnnotation(AssociationSQL.class);
			String[] by = {};
			if(method.getName().contains("c0")) by = by0;
			else if(method.getName().contains("c1")) by = by1;
			System.out.println(String.format(associationSQL.value(), by));
			return this.getDefaultScriptingLanguageInstance().createSqlSource(this, String.format(associationSQL.value(), by), null);
		}).toArray(SqlSource[]::new);
		for(short i=0;i<sqlsources.length;i++) this.addMappedStatement(new MappedStatement.Builder(this, mapper.getName()+methods[i], sqlsources[i], SqlCommandType.SELECT).build());
		break;
	}}
}
}
