package sz.hh.dao;

import org.apache.ibatis.annotations.Param;

import sz.hh.pojo.Users;


public interface UserDao {

	public Users findNameAndPwd(Users users);
}
