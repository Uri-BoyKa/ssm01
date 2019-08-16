package sz.hh.serivce;

import java.util.List;

import sz.hh.pojo.Account;

public interface AccountService {

	public List<Account> findAcByUserid(Integer userid);
	
	public Double findBalanceByAccid(Integer accid);
}
