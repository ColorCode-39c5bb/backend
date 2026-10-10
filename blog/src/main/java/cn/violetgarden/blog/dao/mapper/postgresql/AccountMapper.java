package cn.violetgarden.blog.dao.mapper.postgresql;

import org.apache.ibatis.annotations.Select;

import cn.violetgarden.blog.entity.postgresql.Account;

public interface AccountMapper {
@Select("""
SELECT * FROM public."Account" WHERE number = #{number}
""")
public Account select_by_number(String number);
}
