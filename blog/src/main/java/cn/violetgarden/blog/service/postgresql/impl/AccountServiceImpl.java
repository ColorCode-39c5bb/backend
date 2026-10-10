package cn.violetgarden.blog.service.postgresql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.dao.mapper.postgresql.AccountMapper;
import cn.violetgarden.blog.entity.postgresql.Account;
import cn.violetgarden.blog.service.postgresql.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Account get_account(String number) {
		return accountMapper.select_by_number(number);
	}
}
