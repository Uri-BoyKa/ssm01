package sz.hh.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sz.hh.serivce.UserService;
import sz.hh.util.BalanceLessException;
import sz.hh.util.StoreHouseLessException;

@Controller
@RequestMapping("/sbc")
public class StoreHouseController {
	/*购买书时要实现的步骤
	 * 1.根据书籍编号,查询书籍库存数量  在StoreBuyDao里查询
	 * 2.库存数量减一
	 * 3.查询账户余额	在AccountDao里查询
	 * 4.根据书籍编号，查询书籍价格  通过BookDao查询书籍编号然后获取价格
	 * 5.账户余额减少
	 */
	@Autowired
	private UserService storeBuyImpl;
	
	@RequestMapping(value="buybook",method=RequestMethod.POST)
	public String buybook(Integer bookid,Integer accid,Model model) {
		System.out.println("buybook.....");
		String msg = "购买成功...";
		try {
			boolean flag = storeBuyImpl.buybook(bookid, accid);
			
		} catch (StoreHouseLessException e) {
			 msg = e.getMessage();
			
		} catch (BalanceLessException e) {
			msg = e.getMessage();
		}
		//jsp页面没有把msg传过去,所以没有显示购买成功与否的提示
		model.addAttribute("msg", msg);
		return "pay_info";
	}
}
