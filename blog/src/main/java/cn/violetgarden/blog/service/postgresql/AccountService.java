package cn.violetgarden.blog.service.postgresql;

import cn.violetgarden.blog.entity.postgresql.Account;

public interface AccountService {

	Account get_account(String number);

}
