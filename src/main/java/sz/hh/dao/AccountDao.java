package sz.hh.dao;

import java.util.List;
import java.util.Map;

import sz.hh.pojo.Account;

public interface AccountDao {

	public List<Account> findAcByUserid(Integer userid);
	
	//�����˻����,��ѯ�˻����
	public Double findBalanceByAccid(Integer accid);
	
	//�˻�������
	public void changeBalance(Map<String,Object> map);
}
