package sz.hh.serivce;

import sz.hh.pojo.Users;
import sz.hh.util.BalanceLessException;
import sz.hh.util.StoreHouseLessException;

public interface UserService {

	public Users islogin(Users users);
	
	//¬Ú È
	public boolean buybook(Integer bookid,Integer accid) throws StoreHouseLessException, BalanceLessException;
}
