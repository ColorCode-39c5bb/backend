package cn.violetgarden.blog.dao.mapper.postgresql.association;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;

import cn.violetgarden.blog.entity.postgresql.annotation.AssociationKey;
import cn.violetgarden.blog.entity.postgresql.annotation.PrimaryKey;

public interface AssociationMapper<E0, E1, E2> {
@SelectProvider(type=SQLProvider.class, method="select_association_by")
public <R, T> E2[] select_association_by(Class<T> c, T[] t);
@SelectProvider(type=SQLProvider.class, method="select_entity_by")
public List<Map<String, Object>> select_entity_by(Class<?> c, Object[] t);

static ObjectFactory factory = new DefaultObjectFactory();
static Map<Class<?>, Type[]> cache_generic = new HashMap<>();
default <R, T> Map<T, List<R>> select_by(Class<T> c, T[] t) throws NoSuchFieldException, IllegalAccessException{
	E2[] e2s = select_association_by(c, t);
	Class<?>[] cs = (Class<?>[])cache_generic.get(Stream.of(this.getClass().getInterfaces()).filter(i->cache_generic.get(i)!=null).findFirst().get());

	Object[] r = null;
	if(c==cs[0]) r=select_entity_by(c, t).stream()
		.map(map->factory.create(
			cs[1],
			Stream.of(cs[1].getDeclaredFields()).filter(filed->filed.getAnnotation(AssociationKey.class)==null).<Class<?>>map(filed->filed.getType()).toList(),
			List.copyOf(map.values())
		)).toArray();
	else r=select_entity_by(c, t).stream()
		.map(map->factory.create(
			cs[0],
			Stream.of(cs[0].getDeclaredFields()).filter(filed->filed.getAnnotation(AssociationKey.class)==null).<Class<?>>map(filed->filed.getType()).toList(),
			List.copyOf(map.values())
		)).toArray();
	Map<Object, T> pk0 = Stream.of(t).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<Object, R> pk1 = Stream.of((R[])r).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<T, List<R>> retult = Stream.of(t).collect(Collectors.toMap(k->k, v->new ArrayList<R>()));
	for(E2 e2 : e2s) retult.get(pk0.get(e2.getClass().getDeclaredFields()[0].get(e2))).add(pk1.get(e2.getClass().getDeclaredFields()[1].get(e2)));
	return retult;
}

static Map<Class<?>, Field> cache = new HashMap<>();
private static Object getPrimaryKeyValue(Object entity){
	try {
		if(cache.containsKey(entity.getClass())) return cache.get(entity.getClass()).get(entity);
		Field pk = entity.getClass().getDeclaredField(entity.getClass().getAnnotation(PrimaryKey.class).value());
		cache.put(entity.getClass(), pk);
		return pk.get(entity);
	} catch (NoSuchFieldException | IllegalAccessException e) {
		e.printStackTrace();
		return null;
	}
}

public static class SQLProvider{
public static String select_association_by(ProviderContext context, Class<?> c){
	Class<?>[] cs = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	AssociationMapper.cache_generic.put(context.getMapperType(), cs);
	String[] format = null;
	if(c == cs[0]){ format = new String[]{
		"association.\""+cs[2].getSimpleName()+"\"",
		cs[2].getDeclaredFields()[0].getName(),
		cs[0].getAnnotation(PrimaryKey.class).value()
	};}else if(c == cs[1]){ format = new String[]{
		"association.\""+cs[2].getSimpleName()+"\"",
		cs[2].getDeclaredFields()[1].getName(),
		cs[1].getAnnotation(PrimaryKey.class).value()
	};}else{
		//throw new Exception();
	}
	return String.format("<script>%s</script>", String.format("""
		SELECT * FROM %s WHERE %s IN <foreach collection="t" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>
	""", format));
}
public static String select_entity_by(ProviderContext context, Class<?> c){
	Class<?>[] cs = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	AssociationMapper.cache_generic.put(context.getMapperType(), cs);
	String[] format = null;
	if(c == cs[0]){ format = new String[]{
		"public.\""+cs[1].getSimpleName()+"\".*",
		"public.\""+cs[1].getSimpleName()+"\"",
		"association.\""+cs[2].getSimpleName()+"\"",
		"association.\""+cs[2].getSimpleName()+"\"."+cs[2].getDeclaredFields()[1].getName(),
		"public.\""+cs[1].getSimpleName()+"\"."+cs[1].getAnnotation(PrimaryKey.class).value(),
		cs[2].getDeclaredFields()[0].getName(),
		cs[0].getAnnotation(PrimaryKey.class).value()
	};}else{ format = new String[]{
		"public.\""+cs[0].getSimpleName()+"\".*",
		"public.\""+cs[0].getSimpleName()+"\"",
		"association.\""+cs[2].getSimpleName()+"\"",
		"association.\""+cs[2].getSimpleName()+"\"."+cs[2].getDeclaredFields()[0].getName(),
		"public.\""+cs[0].getSimpleName()+"\"."+cs[0].getAnnotation(PrimaryKey.class).value(),
		cs[2].getDeclaredFields()[1].getName(),
		cs[1].getAnnotation(PrimaryKey.class).value()
	};}
	return String.format("<script>%s</script>", String.format("""
		SELECT %s FROM %s LEFT JOIN %s ON %s = %s
		WHERE %s IN <foreach collection="t" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>
	""", format));
}

}


}