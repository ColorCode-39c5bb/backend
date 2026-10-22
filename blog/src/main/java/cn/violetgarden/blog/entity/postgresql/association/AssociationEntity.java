package cn.violetgarden.blog.entity.postgresql.association;

public abstract class AssociationEntity<TheOther> {
	public abstract TheOther[] get_theother();
}
