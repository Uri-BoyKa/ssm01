package sz.hh.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sz.hh.pojo.Account;
import sz.hh.pojo.Book;
import sz.hh.pojo.Users;
import sz.hh.serivce.AccountService;
import sz.hh.serivce.BookService;

@Controller
@RequestMapping("/ac")
public class AccountController {

	@Autowired
	private AccountService acSer;
	@Autowired
	private BookService bookService;
	
	//查询当前登录账户下有哪些账号可以使用
	@RequestMapping(value="fau")
	public String findAccountByUser(HttpServletRequest request,Model model,Integer bookid) {
		Users u = (Users) request.getSession().getAttribute("myusers");
		//List<Account> aclist = acSer.findAcByUserid(u.getUserid());
		//model.addAttribute("aclist", aclist);
		Book book = bookService.findId(bookid);
		//System.out.println(bookid);
		model.addAttribute("book", book);
		
		
		return "buybook";
	}
	
	//ajax加载异步的方法
	@ResponseBody
	@RequestMapping(value="fa",method=RequestMethod.POST)
	public List<Account> findAccount(HttpServletRequest request){
		Users u = (Users) request.getSession().getAttribute("myusers");
		List<Account> acclist = acSer.findAcByUserid(u.getUserid());
		return acclist;
	}
	
	
	//查询账户的余额    response响应
	@RequestMapping(value="findbalance",method=RequestMethod.POST)
	public void findBalanceByAccid(Integer accid,HttpServletResponse response) throws IOException {
		Double balance = acSer.findBalanceByAccid(accid);
		//System.out.println(balance);
		PrintWriter out = response.getWriter();
		out.print(balance);
		out.flush();
		out.close();
	}
	
	
	
}
