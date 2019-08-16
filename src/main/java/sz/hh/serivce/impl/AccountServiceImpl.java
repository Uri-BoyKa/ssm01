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
	
	//��ѯ�û��˻�
	public List<Account> findAcByUserid(Integer userid) {
		return acDao.findAcByUserid(userid);
	}
	//��ѯ�˻����
	public Double findBalanceByAccid(Integer accid) {
		return acDao.findBalanceByAccid(accid);
	}

}
