package sz.hh.serivce.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import sz.hh.dao.AccountDao;
import sz.hh.dao.BookDao;
import sz.hh.dao.StoreHouseDao;
import sz.hh.dao.UserDao;
import sz.hh.pojo.Users;
import sz.hh.serivce.UserService;
import sz.hh.util.BalanceLessException;
import sz.hh.util.StoreHouseLessException;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	@Autowired
	private StoreHouseDao storeDaoImpl;
	@Autowired
	private BookDao bookDaoImpl;
	@Autowired
	private AccountDao accountDaoImpl;
	
	public Users islogin(Users users) {
		if(users==null) {
			return null;
		}
		if(users.getLoginname()==null||users.getLoginpwd()==null) {
			return null;
		}
		return userdao.findNameAndPwd(users);
	}

	/*������ʱҪʵ�ֵĲ���
	 * 1.�����鼮���,��ѯ�鼮�������  ��StoreBuyDao���ѯ
	 * 2.���������һ
	 * 3.��ѯ�˻����	��AccountDao���ѯ
	 * 4.�����鼮��ţ���ѯ�鼮�۸�  ͨ��BookDao��ѯ�鼮���Ȼ���ȡ�۸�
	 * 5.�˻�������
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackFor= {StoreHouseLessException.class,BalanceLessException.class},readOnly=false,timeout=20)
	public boolean buybook(Integer bookid,Integer accid) throws StoreHouseLessException, BalanceLessException {
		
		//��Ҫ�ѿ�������õ�
		int count = storeDaoImpl.findCountByBookid(bookid);
		if(count<=0) {
			throw new StoreHouseLessException("��治��");
		}
		
		//1.�������Ҫ��һ
		storeDaoImpl.countplusone(bookid);
		//2.�˻����Ҫ����
		Map<String,Object> map = new HashMap<String,Object>();
		Double balance = accountDaoImpl.findBalanceByAccid(accid);//�õ��˻����е����
		Double price = bookDaoImpl.findId(bookid).getBookPrice();//�����鼮���,��ѯ�鼮�۸�
		if(balance - price<0) {
			throw new BalanceLessException("����,�뼰ʱ��ֵ");
		}
		
		Double newmoney = balance - price;
		map.put("newmoney", newmoney);
		map.put("accid",accid);
		accountDaoImpl.changeBalance(map);
		return true;
	}

}
