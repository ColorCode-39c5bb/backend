package cn.violetgarden.blog.entity.postgresql;

public class Tag {
	private String name;
	@Override
	public String toString(){ return String.format(
"""
Tag{
	name='%s'
}
""", name
	);}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
