package sz.hh.dao;

import java.util.List;
import java.util.Map;

import sz.hh.pojo.Account;

public interface AccountDao {

	public List<Account> findAcByUserid(Integer userid);
	
	//根据账户编号,查询账户余额
	public Double findBalanceByAccid(Integer accid);
	
	//账户余额减少
	public void changeBalance(Map<String,Object> map);
}
