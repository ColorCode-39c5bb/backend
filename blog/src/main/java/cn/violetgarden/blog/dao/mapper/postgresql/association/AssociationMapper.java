package cn.violetgarden.blog.dao.mapper.postgresql.association;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface AssociationMapper<C0, C1, C2> {
C2[] select_association_by_c0(C0[] s);
C2[] select_association_by_c1(C1[] s);

C1[] select_by_c0(C0[] s);
C0[] select_by_c1(C1[] s);

@AssociationSQL(value=PreSQL.select_by_cartesianproduct)
Map<C0, C1[]>[] select_by_c0_cartesianproduct(C0[] s);
Map<C1, C0[]>[] select_by_c1_cartesianproduct(C1[] s);

default Map<C0, List<C1>> select_by_c0_manualassembly(C0[] c0s) throws NoSuchFieldException, IllegalAccessException {
	C2[] c2s = select_association_by_c0(c0s);
	C1[] c1s = select_by_c0(c0s);
	Map<Object, C1> pk_c1 = Stream.of(c1s).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<Object, C0> pk_c0 = Stream.of(select_by_c1(c1s)).collect(Collectors.toMap(AssociationMapper::getPrimaryKeyValue, i->i));
	Map<C0, List<C1>> r = Stream.of(c0s).collect(Collectors.toMap(c0->c0, c0->new ArrayList<C1>()));
	for(C2 c2 : c2s); //TODO
	return r;
};
default Map<C1, C0[]>[] select_by_c1_manualassembly(C1[] s){
	return null;
};

static Map<Class<?>, Field> cache = new HashMap<>();
private static Object getPrimaryKeyValue(Object obj){
	try {
		if(cache.containsKey(obj.getClass())) return cache.get(obj.getClass()).get(obj);
		Field pk = obj.getClass().getField(obj.getClass().getAnnotation(PrimaryKey.class).value());
		cache.put(obj.getClass(), pk);
		return pk.get(obj);
	} catch (NoSuchFieldException | IllegalAccessException e) {
		e.printStackTrace();
		return null;
	}
}

static enum PreSQL{
	select_by_cartesianproduct{
		@Override
		public String getSQL(Class<?>[] c){ return String.format(
"""
SELECT * FROM association.%s LEFT JOIN public.%s ON public.%s = association.%s
WHERE %s IN <foreach collection="s" item="i" open="(" close=")" separator=",">#{i.%s}</foreach>		
""", null);
		}
	};
	public abstract String getSQL(Class<?>[] classes);
}
}