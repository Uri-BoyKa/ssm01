package sz.hh.serivce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.hh.dao.AccountDao;
import sz.hh.pojo.Account;
import sz.hh.serivce.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao acDao;
	
	//查询用户账户
	public List<Account> findAcByUserid(Integer userid) {
		return acDao.findAcByUserid(userid);
	}
	//查询账户余额
	public Double findBalanceByAccid(Integer accid) {
		return acDao.findBalanceByAccid(accid);
	}

}
