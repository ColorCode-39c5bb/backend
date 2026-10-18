package cn.violetgarden.blog.entity.postgresql;

public class Account {
	private	String number;
	private Object profile;
	private String nick;

	@Override
	public String toString(){ return String.format(
"""
Account{
	number='%s',
	profile=%s,
	nick='%s'
}
"""
	, number, profile, nick);}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Object getProfile() {
		return profile;
	}

	public void setProfile(Object profile) {
		this.profile = profile;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	
}
