package sz.hh.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sz.hh.dao.StoreHouseDao;
import sz.hh.pojo.Users;
import sz.hh.serivce.UserService;
@Controller
@RequestMapping("/uc")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="login")
	public String islogin(Users user,HttpServletRequest request) {
		Users users = userService.islogin(user);
		if(users==null) {
			return "login";
		}
		request.getSession().setAttribute("myusers", users);
		return "redirect:/bc/fid";
	}
	
	@RequestMapping(value="tiao")
	public String tiao() {
		
		return "addbooknew";
	}
	
	
	
	
}
