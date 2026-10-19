package cn.violetgarden.blog.dao.mapper.postgresql.association;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderContext;

public interface AssociationMapper<C0, C1, C2> {
@SelectProvider(type=SQLProvider.class, method="select_association_by_c0")
C2[] select_association_by_c0(C0[] s);
@SelectProvider(type=SQLProvider.class, method="select_association_by_c1")
C2[] select_association_by_c1(C1[] s);

@SelectProvider(type=SQLProvider.class, method="select_by_c0")
C1[] select_by_c0(C0[] s);
@SelectProvider(type=SQLProvider.class, method="select_by_c1")
C0[] select_by_c1(C1[] s);

//Map<C0, C1[]> select_by_c0_cartesianproduct(C0[] s);
//Map<C1, C0[]> select_by_c1_cartesianproduct(C1[] s);

default Map<C0, List<C1>> select_by_c0_manualassembly(C0[] c0s) throws NoSuchFieldException, IllegalAccessException{
	C2[] c2s = select_association_by_c0(c0s);
	C1[] c1s = select_by_c0(c0s);
	Map<Object, C1> pk_c1 = Stream.of(c1s).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<Object, C0> pk_c0 = Stream.of(c0s).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<C0, List<C1>> r = Stream.of(c0s).collect(Collectors.toMap(c0->c0, c0->new ArrayList<C1>()));
	for(C2 c2 : c2s) r.get(pk_c0.get(c2.getClass().getDeclaredFields()[0].get(c2))).add(pk_c1.get(c2.getClass().getDeclaredFields()[1].get(c2)));
	return r;
};
default Map<C1, List<C0>> select_by_c1_manualassembly(C1[] c1s) throws NoSuchFieldException, IllegalAccessException {
	C2[] c2s = select_association_by_c1(c1s);
	C0[] c0s = select_by_c1(c1s);
	Map<Object, C0> pk_c0 = Stream.of(c0s).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<Object, C1> pk_c1 = Stream.of(c1s).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<C1, List<C0>> r = Stream.of(c1s).collect(Collectors.toMap(c1->c1, c1->new ArrayList<C0>()));
	for(C2 c2 : c2s) r.get(pk_c1.get(c2.getClass().getDeclaredFields()[1].get(c2))).add(pk_c0.get(c2.getClass().getDeclaredFields()[0].get(c2)));
	return r;
};

static Map<Class<?>, Field> cache = new HashMap<>();
private static Object getPrimaryKeyValue(Object obj){
	try {
		if(cache.containsKey(obj.getClass())) return cache.get(obj.getClass()).get(obj);
		Field pk = obj.getClass().getDeclaredField(obj.getClass().getAnnotation(PrimaryKey.class).value());
		cache.put(obj.getClass(), pk);
		return pk.get(obj);
	} catch (NoSuchFieldException | IllegalAccessException e) {
		e.printStackTrace();
		return null;
	}
}

public static class SQLProvider{
public String select_association_by_c0(ProviderContext context){
	Class<?>[] c = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	return String.format("<script>%s</script>", String.format("""
		SELECT * FROM %s WHERE %s IN <foreach collection="s" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>""",
	"association.\""+c[2].getSimpleName()+"\"",
	c[2].getDeclaredFields()[0].getName(), 
	c[0].getAnnotation(PrimaryKey.class).value()
	));
}
public String select_association_by_c1(ProviderContext context){
	Class<?>[] c = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	return String.format("<script>%s</script>", String.format("""
			SELECT * FROM %s WHERE %s IN <foreach collection="s" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>""",
		"association.\""+c[2].getSimpleName()+"\"",
		c[2].getDeclaredFields()[1].getName(), 
		c[1].getAnnotation(PrimaryKey.class).value()
	));
}
public String select_by_c0(ProviderContext context){
	Class<?>[] c = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	return String.format("<script>%s</script>", String.format("""
		SELECT %s FROM %s LEFT JOIN %s ON %s = %s
		WHERE %s IN <foreach collection="s" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>""",
	"public.\""+c[1].getSimpleName()+"\".*",
	"public.\""+c[1].getSimpleName()+"\"",
	"association.\""+c[2].getSimpleName()+"\"",
	"association.\""+c[2].getSimpleName()+"\"."+c[2].getDeclaredFields()[1].getName(),
	"public.\""+c[1].getSimpleName()+"\"."+c[1].getAnnotation(PrimaryKey.class).value(),
	c[2].getDeclaredFields()[0].getName(),
	c[0].getAnnotation(PrimaryKey.class).value()
	));
}
public String select_by_c1(ProviderContext context){
	Class<?>[] c = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	return String.format("<script>%s</script>", String.format("""
		SELECT %s FROM %s LEFT JOIN %s ON %s = %s
		WHERE %s IN <foreach collection="s" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>""",
	"public.\""+c[0].getSimpleName()+"\".*",
	"public.\""+c[0].getSimpleName()+"\"",
	"association.\""+c[2].getSimpleName()+"\"",
	"association.\""+c[2].getSimpleName()+"\"."+c[2].getDeclaredFields()[0].getName(),
	"public.\""+c[0].getSimpleName()+"\"."+c[0].getAnnotation(PrimaryKey.class).value(),
	c[2].getDeclaredFields()[1].getName(),
	c[1].getAnnotation(PrimaryKey.class).value()
	));
}
public String select_by_c0_cartesianproduct(ProviderContext context){
	Class<?>[] c = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	return String.format("<script>%s</script>", String.format("""
		SELECT * FROM %s LEFT JOIN %s ON %s = %s
		WHERE %s IN <foreach collection="s" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>""",
	"association.\""+c[2].getSimpleName()+"\"",
	"public.\""+c[1].getSimpleName()+"\"",
	"public.\""+c[1].getSimpleName()+"\"."+c[1].getAnnotation(PrimaryKey.class).value(),
	"association.\""+c[2].getSimpleName()+"\"."+c[2].getDeclaredFields()[1].getName(),
	c[2].getDeclaredFields()[0].getName(), 
	c[0].getAnnotation(PrimaryKey.class).value()
	));
};
public String select_by_c1_cartesianproduct(ProviderContext context){
	Class<?>[] c = Stream.of(((ParameterizedType)Stream.of(context.getMapperType().getGenericInterfaces()).filter(type->type instanceof ParameterizedType&&((ParameterizedType)type).getRawType()==AssociationMapper.class).findFirst().get()).getActualTypeArguments()).map(t->(Class<?>)t).toArray(Class<?>[]::new);
	return String.format("<script>%s</script>", String.format("""
		SELECT * FROM %s LEFT JOIN %s ON %s = %s
		WHERE %s IN <foreach collection="s" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>""",
	"association.\""+c[2].getSimpleName()+"\"",
	"public.\""+c[0].getSimpleName()+"\"",
	"public.\""+c[0].getSimpleName()+"\"."+c[0].getAnnotation(PrimaryKey.class).value(),
	"association.\""+c[2].getSimpleName()+"\"."+c[2].getDeclaredFields()[0].getName(),
	c[2].getDeclaredFields()[1].getName(), 
	c[1].getAnnotation(PrimaryKey.class).value()
	));
}
}
}
