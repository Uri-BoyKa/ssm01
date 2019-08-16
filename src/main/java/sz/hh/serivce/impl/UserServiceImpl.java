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

	/*购买书时要实现的步骤
	 * 1.根据书籍编号,查询书籍库存数量  在StoreBuyDao里查询
	 * 2.库存数量减一
	 * 3.查询账户余额	在AccountDao里查询
	 * 4.根据书籍编号，查询书籍价格  通过BookDao查询书籍编号然后获取价格
	 * 5.账户余额减少
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackFor= {StoreHouseLessException.class,BalanceLessException.class},readOnly=false,timeout=20)
	public boolean buybook(Integer bookid,Integer accid) throws StoreHouseLessException, BalanceLessException {
		
		//先要把库存数量拿到
		int count = storeDaoImpl.findCountByBookid(bookid);
		if(count<=0) {
			throw new StoreHouseLessException("库存不足");
		}
		
		//1.库存数量要减一
		storeDaoImpl.countplusone(bookid);
		//2.账户余额要减少
		Map<String,Object> map = new HashMap<String,Object>();
		Double balance = accountDaoImpl.findBalanceByAccid(accid);//得到账户现有的余额
		Double price = bookDaoImpl.findId(bookid).getBookPrice();//根据书籍编号,查询书籍价格
		if(balance - price<0) {
			throw new BalanceLessException("余额不足,请及时充值");
		}
		
		Double newmoney = balance - price;
		map.put("newmoney", newmoney);
		map.put("accid",accid);
		accountDaoImpl.changeBalance(map);
		return true;
	}

}
